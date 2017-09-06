package org.formation.faces.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.formation.model.Advisor;
import org.formation.model.Customer;

import org.formation.service.IServiceAdvisor;
import org.formation.service.IServiceCustomer;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "advisorMB")
@ViewScoped
public class AdvisorMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	IServiceAdvisor advisor;

	@Autowired
	IServiceCustomer customer;

	private Advisor bean;
	private Advisor beanSelected;
	private List<Advisor> list;
	private List<Advisor> listSelected;
	private List<String> listCustomer;

	@PostConstruct
	public void init() {
		refreshList();
	}

	public void refreshList() {
		this.bean = new Advisor();
		this.beanSelected = new Advisor();
		this.list = new ArrayList<Advisor>();
		this.listSelected = new ArrayList<Advisor>();
		this.listCustomer = new ArrayList<String>();
		try {
			this.list.addAll(advisor.findAll());
			this.listSelected.addAll(list);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}


	public void save() {
		try {
			for (String customerId : listCustomer) {
				Customer c = new Customer();
				c = customer.findById(Long.parseLong(customerId));

				this.bean.getCustomers().add(c);
			}
			advisor.merge(this.bean);
			refreshList();
			notificationSuccess("persist advisor");
		} catch (Exception e) {
			notificationError(e, "persist advisor");
			e.printStackTrace();
		}
	}

	public void update() {
		try {
			advisor.merge(this.beanSelected);

			refreshList();
			notificationSuccess("update advisor");
		} catch (Exception e) {
			notificationError(e, "update advisor");
		}
	}

	public void delete() {
		try {
			advisor.remove(this.beanSelected.getId());
			refreshList();
			notificationSuccess("delete advisor");
		} catch (Exception e) {
			notificationError(e, "delete advisor");
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
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Operation " + operation + " success");
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Opération effectuée");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void notificationError(Exception e, String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.ERROR, "Operation " + operation + " Error ", e);
		FacesMessage msg = null;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Une erreur est survenue");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Customer> getAllCustomers() {
		List<Customer> cList = new ArrayList<Customer>();
		try {
			cList.addAll(customer.findAll());
		} catch (Exception e) {

			e.printStackTrace();
		}
		return cList;
	}

	public IServiceAdvisor getAdvisor() {
		return advisor;
	}

	public void setAdvisor(IServiceAdvisor advisor) {
		this.advisor = advisor;
	}

	public Advisor getBean() {
		return bean;
	}

	public void setBean(Advisor bean) {
		this.bean = bean;
	}

	public Advisor getBeanSelected() {
		return beanSelected;
	}

	public void setBeanSelected(Advisor beanSelected) {
		this.beanSelected = beanSelected;
	}

	public List<Advisor> getList() {
		return list;
	}

	public void setList(List<Advisor> list) {
		this.list = list;
	}

	public List<Advisor> getListSelected() {
		return listSelected;
	}

	public IServiceCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(IServiceCustomer customer) {
		this.customer = customer;
	}

	public List<String> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<String> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public void setListSelected(List<Advisor> listSelected) {
		this.listSelected = listSelected;
	}

}
