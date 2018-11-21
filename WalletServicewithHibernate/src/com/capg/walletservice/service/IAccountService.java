package com.capg.walletservice.service;

import com.capg.walletservice.bean.AccountBean;
import com.capg.walletservice.bean.CustomerBean;

public interface IAccountService {

	public boolean createAccount(AccountBean accountBean, CustomerBean bean)
			throws Exception;

	// public AccountBean findAccount(int accountId) throws Exception;
	public boolean deposit(AccountBean accountBean, double depositAmount)
			throws Exception;

	public boolean withdraw(AccountBean accountBean, double withdrawAmount)
			throws Exception;

	public boolean fundTransfer(AccountBean accountBeanFrom,
			AccountBean accountBeanTo, double transferAmount) throws Exception;

	AccountBean findAccount(int accountId, String mobileNumber)
			throws Exception;

	public double showBalance(double balance);

}
