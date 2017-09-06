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

import org.formation.model.Customer;

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
//	CustomerDao customer;
	
//	@Autowired
//	ItemsDao itemService;
	
	private Customer bean;
	private Customer beanSelected;
	private List<Customer> list;
	private List<Customer> listSelected;
//	private List<String> listAccount;
	
	
	
	@PostConstruct
    public void init() {
		refreshList();
    }
	
	
	public void refreshList() {
		this.bean = new Customer();
		this.beanSelected = new Customer();
		this.list = new ArrayList<Customer>();
		this.listSelected = new ArrayList<Customer>();
//		this.listAccount = new ArrayList<String>();
		try {
			this.list.addAll(customer.findAll());
			this.listSelected.addAll(list);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
	  
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

	
	
//	public void save() {
//		try { 
//			for(String customerId : listCustomer){
//				Customer c = new Customer();
////				Item item = new Item();
//				c = customer.findById(Long.parseLong(customerId));
//				
////				this.bean.getItems().add(item);
//			}
//			this.bean.setOrderDate(new Date());
//			// Use merge instead of persist or you'll have a org.hibernate.PersistentObjectException: detached entity passed to persist: org.slevin.common.Item
//			orderService.merge(this.bean);
//			refreshList();
//			notificationSuccess("persist order");
//		} catch (Exception e) {
//			notificationError(e,"persist order");
//			e.printStackTrace();
//		}
//	}
	
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
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Success"); 
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}


	public void notificationError(Exception e, String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.ERROR, "Operation "+operation+" Error ",e);
		FacesMessage msg = null;  
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Une erreur est survenue");  
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	
//	public List<Item> getAllItems() {
//		List<Item> tmpList = new ArrayList<Item>();
//		try {
//			tmpList.addAll(itemService.findAll());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return tmpList;
//	}


	
	public IServiceCustomer getCustomer() {
		return customer;
	}
	
	
	public void setCustomer(IServiceCustomer customer) {
		this.customer = customer;
	}

	public Customer getBean() {
		return bean;
	}




//	public CustomerDao getCustomer() {
//		return customer;
//	}
//
//
//	public void setCustomer(CustomerDao customer) {
//		this.customer = customer;
//	}


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


//	public ItemsDao getItemService() {
//		return itemService;
//	}
//
//
//	public void setItemService(ItemsDao itemService) {
//		this.itemService = itemService;
//	}


//	public List<String> getListItem() {
//		return listItem;
//	}
//
//
//	public void setListItem(List<String> listItem) {
//		this.listItem = listItem;
//	}


	
	
	
	

}
