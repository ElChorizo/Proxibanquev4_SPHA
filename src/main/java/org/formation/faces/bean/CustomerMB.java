package org.formation.faces.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.formation.dao.CustomerDao;
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


@Component(value="customerMB")
@ViewScoped
public class CustomerMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	IServiceCustomer customer;
	
	@Autowired
	IServiceAccount serviceAccount;
	
	private Customer bean;
	private Customer beanSelected;
	private List<Customer> list;
	private List<Customer> listSelected;
	private List<String> listAccount;
	
	
	
	@PostConstruct
    public void init() {
		refreshList();
    }
	
	
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
	
	public void save() {
		try {
			Account check = new CheckingAccount();
			Account savings = new SavingsAccount();
			this.bean.addAccount(check);
			this.bean.addAccount(savings);
			customer.persist(this.bean);
			System.out.println(this.bean);
			refreshList();
			notificationSuccess("persist item");
		} catch (Exception e) {
			notificationError(e,"persist item");
			System.out.println("pb :"+this.bean);
			e.printStackTrace();
		}
	}

	
	public void update() {
		try {
			customer.merge(this.beanSelected);
			
			refreshList();
			notificationSuccess("update order");
		} catch (Exception e) {
			notificationError(e,"update order");
		}
	}
	
	
	public void delete() {
		try {
			customer.remove(this.beanSelected.getId());
			refreshList();
			notificationSuccess("delete order");
		} catch (Exception e) {
			notificationError(e,"delete order");
		}
	}
	
	public void onCancel(RowEditEvent event) {
		refreshList();
	}
	
	public void reset() {
		refreshList();
        RequestContext.getCurrentInstance().reset("form1:panel");  
	}
	
	public void notificationSuccess(String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Operation "+operation+" success");
		FacesMessage msg = null;  
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Opération effectuée"); 
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}


	public void notificationError(Exception e, String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.ERROR, "Operation "+operation+" Error ",e);
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


	
	
}
