package org.formation.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("savingsAccount")
public class SavingsAccount extends Account{
	
	private double rates = 1.03;
	
	public SavingsAccount() {
		super();
	}

	public SavingsAccount(String date, double balance, Customer customer) {
		super(date, balance, customer);
	}

	
	public SavingsAccount(double rates) {
		super();
		this.rates = rates;
	}

	public double getRates() {
		return rates;
	}

	public void setRates(double rates) {
		this.rates = rates;
	}
	
}
