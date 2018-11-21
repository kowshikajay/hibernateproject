package com.capg.walletservice.dao;

import com.capg.walletservice.bean.AccountBean;

public interface IAccountDAO {

	public boolean createAccount(AccountBean accountBean) throws Exception;

	public boolean updateAccount(AccountBean accountBean) throws Exception;

	// public AccountBean findAccount(int accountId) throws Exception;

	public AccountBean findAccount(int accountId, String mobileNumber)
			throws Exception;

	boolean deposit(AccountBean accountBean, double depositAmount)
			throws Exception;

	boolean withdraw(AccountBean accountBean, double withdrawAmount)
			throws Exception;

	boolean fundTransfer(AccountBean accountBeanFrom,
			AccountBean accountBeanTo, double transferAmount) throws Exception;
	public double showBalance(double balance);
}
