package com.capg.walletservice.dao;

import javax.persistence.EntityManager;

import com.capg.walletservice.bean.AccountBean;
import com.capg.walletservice.bean.CustomerBean;

public class AccountDAOImp implements IAccountDAO {

	EntityManager em;

	@Override
	public boolean createAccount(AccountBean accountBean) throws Exception {
		try {

			this.em = JPAManager.createEntityManager();
			em.getTransaction().begin();

			em.persist(accountBean);

			em.getTransaction().commit();
			JPAManager.closeResources(em);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public double showBalance(double balance) {
		AccountBean accountBean = new AccountBean();
		balance = accountBean.getBalance();
		return balance;
	}

	@Override
	public boolean deposit(AccountBean accountBean, double depositAmount)
			throws Exception {

		accountBean.setBalance(accountBean.getBalance() + depositAmount);

		boolean result = updateAccount(accountBean);
		return result;
	}

	@Override
	public boolean withdraw(AccountBean accountBean, double withdrawAmount)
			throws Exception {
		accountBean.setBalance(accountBean.getBalance() - withdrawAmount);

		boolean result = updateAccount(accountBean);
		return result;
	}

	@Override
	public boolean fundTransfer(AccountBean accountBeanFrom,
			AccountBean accountBeanTo, double transferAmount) throws Exception {

		accountBeanFrom.setBalance(accountBeanFrom.getBalance()
				- transferAmount);
		accountBeanTo.setBalance(accountBeanTo.getBalance() + transferAmount);

		boolean result1 = updateAccount(accountBeanFrom);
		boolean result2 = updateAccount(accountBeanTo);
		return result1 && result2;
	}

	@Override
	public boolean updateAccount(AccountBean accountBean) throws Exception {
		try {
			this.em = JPAManager.createEntityManager();
			em.getTransaction().begin();

			em.merge(accountBean);

			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public AccountBean findAccount(int accountId, String mobileNumber)
			throws Exception {
		AccountBean result = new AccountBean();
		
		try {

			em = JPAManager.createEntityManager();
			em.getTransaction().begin();
			AccountBean accountBean2 = em.find(AccountBean.class, accountId);
			if (mobileNumber ==accountBean2.getCustomerBean().getMobileNumber()) {
				result = accountBean2;
			}
			JPAManager.closeResources(em);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}

		return result;

	}

}
