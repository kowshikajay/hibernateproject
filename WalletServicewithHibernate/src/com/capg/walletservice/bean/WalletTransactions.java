package com.capg.walletservice.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="Wallet_trans1")
public class WalletTransactions {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionId;
	
	private double transactionAmount;
	
	private int transactionType;
	// 1- deposit 2- withdraw 3-fund transfer
	
	@Transient
	private AccountBean accountBeanTo; // Account which received money
	
	
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	
	
	public AccountBean getAccountBeanTo() {
		return accountBeanTo;
	}
	public void setAccountBeanTo(AccountBean accountBeanTo) {
		this.accountBeanTo = accountBeanTo;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
	@Override
	public String toString() {
		return "WalletTransactions [transactionId=" + transactionId
				+ ", transactionAmount=" + transactionAmount
				+ ", transactionType=" + transactionType
				+ ", accountBeanTo=" + accountBeanTo
				+ ", transactionDate=" + transactionDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + transactionId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WalletTransactions other = (WalletTransactions) obj;
		if (transactionId != other.transactionId)
			return false;
		return true;
	}
	
	
	

}

