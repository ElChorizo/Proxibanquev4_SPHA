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
import org.formation.model.Account;

import org.formation.service.IServiceAccount;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="accountMB")
@ViewScoped
public class AccountMB implements Serializable{


	private static final long serialVersionUID = -8645699749357040704L;

	@Autowired
	IServiceAccount accountService;
	
	private Account bean;
	private Account beanSelected;
	private List<Account> list;
	private List<Account> listSelected;
	
	@PostConstruct
    public void init() {
		refreshList();
    }
	
	public void refreshList() {
		this.bean = new Account();
		this.beanSelected = new Account();
		this.list = new ArrayList<Account>();
		this.listSelected = new ArrayList<Account>();
		try {
			this.list.addAll(accountService.findAll());
			this.listSelected.addAll(list);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
		    
			accountService.persist(this.bean);
			refreshList();
			notificationSuccess("persist item");
		} catch (Exception e) {
			notificationError(e,"persist item");
			e.printStackTrace();
		}
	}
	
	public void delete() {
		try {
			accountService.remove(this.beanSelected.getAccountNumber());
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

	public Account getBean() {
		return bean;
	}

	public void setBean(Account bean) {
		this.bean = bean;
	}

	public Account getBeanSelected() {
		return beanSelected;
	}

	public void setBeanSelected(Account beanSelected) {
		this.beanSelected = beanSelected;
	}

	public List<Account> getList() {
		return list;
	}

	public void setList(List<Account> list) {
		this.list = list;
	}

	public List<Account> getListSelected() {
		return listSelected;
	}

	public void setListSelected(List<Account> listSelected) {
		this.listSelected = listSelected;
	}
	
	
	
}
