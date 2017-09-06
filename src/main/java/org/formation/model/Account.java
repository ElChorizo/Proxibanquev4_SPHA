package org.formation.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="AccountType")
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long accountNumber;
	private String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
	private double balance;
	@ManyToOne
	private Customer customer;
	
	public Account() {
		super();
	}
	
	
	public Account(String date, double balance, Customer customer) {
		super();
		this.date = date;
		this.balance = balance;
		this.customer = customer;
	}


	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
