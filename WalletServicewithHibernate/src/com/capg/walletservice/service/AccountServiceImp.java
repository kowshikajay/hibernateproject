package com.capg.walletservice.service;

import com.capg.walletservice.bean.AccountBean;
import com.capg.walletservice.bean.CustomerBean;
import com.capg.walletservice.dao.AccountDAOImp;
import com.capg.walletservice.dao.IAccountDAO;
import com.capg.walletservice.exception.CustomerException;
import com.capg.walletservice.exception.CustomerExceptionMessage;

public class AccountServiceImp implements IAccountService {

	@Override
	public boolean createAccount(AccountBean accountBean, CustomerBean bean)
			throws Exception {
		IAccountDAO dao = new AccountDAOImp();

		boolean result = false;
		try {
			if (bean.getFirstName().length() < 5) {

				throw new Exception(CustomerExceptionMessage.ERROR1);
			} else if (bean.getLastName().length() < 5) {

				throw new Exception(CustomerExceptionMessage.ERROR2);
			}

			else if (!(bean.getEmailId().matches("[a-z0-9]+@gmail\\.com"))) {

				throw new CustomerException(CustomerExceptionMessage.ERROR3);

			} else if (!(bean.getPanNum().matches("[A-Z]{4}[0-9]{5}[A-Z]{1}"))) {

				throw new CustomerException(CustomerExceptionMessage.ERROR4);

			} else if (!(bean.getMobileNumber().matches("[7-9][0-9]{9}"))) {

				throw new Exception(CustomerExceptionMessage.ERROR5);

			} else if (bean.getAddress().trim().length() == 0) {

				throw new CustomerException(CustomerExceptionMessage.ERROR7);

			} else {

				result = dao.createAccount(accountBean);

			}
		} catch (NullPointerException e) {

		}
		return true;
	}

	public double showBalance(double balance) {
		IAccountDAO dao = new AccountDAOImp();
		return dao.showBalance(balance);

	}

	@Override
	public boolean deposit(AccountBean accountBean, double depositAmount)
			throws Exception {
		IAccountDAO dao = new AccountDAOImp();
		return dao.deposit(accountBean, depositAmount);
	}

	@Override
	public boolean withdraw(AccountBean accountBean, double withdrawAmount)
			throws Exception {
		IAccountDAO dao = new AccountDAOImp();
		return dao.withdraw(accountBean, withdrawAmount);
	}

	@Override
	public boolean fundTransfer(AccountBean accountBeanFrom,
			AccountBean accountBeanTo, double transferAmount) throws Exception {
		IAccountDAO dao = new AccountDAOImp();
		return dao.fundTransfer(accountBeanFrom, accountBeanTo, transferAmount);
	}

	@Override
	public AccountBean findAccount(int accountId, String mobileNumber)
			throws Exception {
		IAccountDAO dao = new AccountDAOImp();
		return dao.findAccount(accountId, mobileNumber);
	}

}
