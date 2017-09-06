package org.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name="Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String firstname;
	private String address;
	private String email;
	private String tel;
	private String cp;
	private String town;
	
	@OneToMany (cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
    private List<Account> listAccount=new ArrayList<>();
	
		
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	
	
	public Customer() {
		super();
		
	}


	public Customer(String tel, String cp) {
		super();
		this.tel = tel;
		this.cp = cp;

	}
		

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getCp() {
		return cp;
	}


	public void setCp(String cp) {
		this.cp = cp;
	}



	public List<Account> getListAccount() {
		return listAccount;
	}

	public void setListAccount(List<Account> listAccount) {
		this.listAccount = listAccount;
	}

	public void addAccount(Account account) {
		listAccount.add(account);
		account.setCustomer(this);
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", firstname=" + firstname + ", address=" + address
				+ ", email=" + email + ", tel=" + tel + ", cp=" + cp + ", town=" + town + "]";
	}


	
	

}
