package org.formation.faces.bean;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.formation.model.Account;
import org.formation.model.CheckingAccount;
import org.formation.model.Customer;
import org.formation.model.SavingsAccount;
import org.formation.service.IServiceAccount;
import org.formation.service.IServiceCustomer;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author AL, SRL, PHL
 *
 */
@Component(value = "customerMB")
@ViewScoped
public class CustomerMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	IServiceCustomer customer;

	@Autowired
	IServiceAccount serviceAccount;

	@Autowired
	AccountMB accountBean;

	private Customer bean;
	private Customer beanSelected;
	private List<Customer> list;
	private List<Customer> listSelected;
	private List<String> listAccount;

	@PostConstruct
	public void init() {
		refreshList();

	}

	/**
	 * Cette m�thode permet de charger le contenu et d'actualiser les pages web qui
	 * l'appellent (notamment la page views/customer/all.xhtml,
	 * views/customer/new.xhtml)
	 */
	public void refreshList() {
		this.bean = new Customer();
		this.beanSelected = new Customer();
		this.list = new ArrayList<Customer>();
		this.listSelected = new ArrayList<Customer>();
		this.listAccount = new ArrayList<String>();
		try {
			this.list.addAll(customer.findAll());
			this.listSelected.addAll(list);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Cette m�thode permet de sauvegarder un nouveau client en base de donn�es, en
	 * lui associant un compte �pargne et un compte courant.
	 */
	public void save() {
		try {
			Account check = new CheckingAccount();
			Account savings = new SavingsAccount();
			this.bean.addAccount(check);
			this.bean.addAccount(savings);
			customer.persist(this.bean);
			refreshList();
			accountBean.refreshList();
			notificationSuccess("persist item");
		} catch (Exception e) {
			notificationError(e, "persist item");
			e.printStackTrace();
		}
	}

	/**
	 * Cette m�thode permet de mettre � jour les informations d'un client existant
	 * en base de donn�es. La mise � jour est affich�e dans la liste des clients
	 * gr�ce � l'appel � la m�thode refreshList().
	 */
	public void update() {
		try {
			customer.merge(this.beanSelected);

			refreshList();
			accountBean.refreshList();
			notificationSuccess("update order");
		} catch (Exception e) {
			notificationError(e, "update order");
		}
	}

	/**
	 * Cette m�thode permet de supprimer un client existant en base de donn�es, et
	 * les comptes associ�s.
	 */
	public void delete() {
		try {
			customer.remove(this.beanSelected.getId());
			refreshList();
			accountBean.refreshList();
			notificationSuccess("delete order");
		} catch (Exception e) {
			notificationError(e, "delete order");
		}
	}

	public void onCancel(RowEditEvent event) {
		refreshList();
	}

	/**
	 * Cette m�thode est appel�e lorsque l'utilisateur se sert de la fonction
	 * "annuler" dans les formulaires. Le formulaire revient � son �tat initial.
	 */
	public void reset() {
		refreshList();
		RequestContext.getCurrentInstance().reset("form1:panel");
	}

	/**
	 * Cette m�thode permet de signaler qu'une op�ration (par exemple supprimer un
	 * client) a �t� effectu�e avec succ�s.
	 */
	public void notificationSuccess(String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Operation " + operation + " success");
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Op�ration effectu�e");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * Cette m�thode permet de signaler qu'une erreur est survenue lors d'une
	 * op�ration (par exemple, ajouter un client)
	 *
	 */
	public void notificationError(Exception e, String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.ERROR, "Operation " + operation + " Error ", e);
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Une erreur est survenue");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Account> getAllAccounts() {
		List<Account> tmpList = new ArrayList<Account>();
		try {
			tmpList.addAll(serviceAccount.findByProperty("customer_id", this.beanSelected.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmpList;
	}

	public IServiceCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(IServiceCustomer customer) {
		this.customer = customer;
	}

	public IServiceAccount getServiceAccount() {
		return serviceAccount;
	}

	public void setServiceAccount(IServiceAccount serviceAccount) {
		this.serviceAccount = serviceAccount;
	}

	public Customer getBean() {
		return bean;
	}

	public void setBean(Customer bean) {
		this.bean = bean;
	}

	public Customer getBeanSelected() {
		return beanSelected;
	}

	public void setBeanSelected(Customer beanSelected) {
		this.beanSelected = beanSelected;
	}

	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}

	public List<Customer> getListSelected() {
		return listSelected;
	}

	public void setListSelected(List<Customer> listSelected) {
		this.listSelected = listSelected;
	}

	public List<String> getListAccount() {
		return listAccount;
	}

	public void setListAccount(List<String> listAccount) {
		this.listAccount = listAccount;
	}

	/**
	 * Cette m�thode permet de se d�connecter. L'utilisateur est redirig� vers la
	 * page d'accueil.
	 */
	public String logOut() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.invalidateSession();
		return "home?faces-redirect=true";
	}

}
