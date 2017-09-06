package org.formation.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("checkingAccount")
public class CheckingAccount extends Account {

	private double Overdraft= -1000;

	public CheckingAccount() {
		super();
		
	}

	public CheckingAccount(String date, double balance, Customer customer) {
		super(date, balance, customer);
	}

	
	public CheckingAccount(double overdraft) {
		super();
		Overdraft = overdraft;
	}

	public double getOverdraft() {
		return Overdraft;
	}

	public void setOverdraft(double overdraft) {
		Overdraft = overdraft;
	}

	
	
	
	
	
}
