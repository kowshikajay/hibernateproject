package com.capg.walletservice.ui;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.capg.walletservice.bean.AccountBean;
import com.capg.walletservice.bean.CustomerBean;
import com.capg.walletservice.bean.WalletTransactions;
import com.capg.walletservice.exception.CustomerExceptionMessage;
import com.capg.walletservice.service.AccountServiceImp;
import com.capg.walletservice.service.IAccountService;

public class Client {

	public static void main(String[] args) throws Exception {
		IAccountService service = new AccountServiceImp();
		CustomerBean customer = new CustomerBean();
		Scanner scanner = new Scanner(System.in);
		char ch;
		Client client = new Client();
		do {

			System.out
					.println(" 1. Creating Account\n 2. Show Balance \n 3. Deposit \n 4. Withdraw \n "
							+ "5. Fund Transfer \n 6. Print Transactions \n 7. Exit\n ");
			System.out.println("Enter your choice");
			int n = scanner.nextInt();

			switch (n) {
			case 1:

				// create account

				System.out.print("\tEnter your firstname\t\t:");
				String firstname = scanner.next();

				System.out.print("\tEnter your lastname\t\t:");
				String lastname = scanner.next();

				System.out.print("\tEnter  your  mobile number\t\t:");
				String mobile = scanner.next();
				System.out.print("\tEnter  your  email id\t\t:");
				String email = scanner.next();

				System.out.print("\tEnter  your PAN number\t\t:");
				String pan = scanner.next();

				System.out.print("\tEnter  your  address\t\t:");
				String address = scanner.next();

				CustomerBean customerBean = new CustomerBean();
				customerBean.setAddress(address);
				customerBean.setEmailId(email);
				customerBean.setPanNum(pan);
				customerBean.setMobileNumber(mobile);
				customerBean.setFirstName(firstname);
				customerBean.setLastName(lastname);

				System.out.print("\tEnter balance to create account\t\t:");
				double balance = scanner.nextDouble();

				if (balance < 500) {

					throw new Exception(CustomerExceptionMessage.ERROR6);
				}
				AccountBean accountBean = new AccountBean();

				accountBean.setBalance(balance);
				accountBean.setInitialDeposit(balance);
				accountBean.setCustomerBean(customerBean);

				accountBean.setDateOfRegistration(new Date());

				boolean result = service.createAccount(accountBean,
						customerBean);

				if (result) {
					System.out
							.println("\tCongratulations!! Your account has been created successfully...\n\t");
					System.out
							.println("\t...Thank you for choosing our bank...\n\t");
				} else {
					System.err.println("\n\n\t\tEnter valid details..\n\n\t\t");
				}
				break;
			case 2:

				// show balance

				System.out.println("Enter Account ID");
				int accId = scanner.nextInt();
				System.out.println("Enter Mobile Number");
				String mobileNumber = scanner.next();

				accountBean = service.findAccount(accId, mobileNumber);

				if (accountBean == null) {
					System.out.println("Account Does not exist");
					return;
				}

				balance = accountBean.getBalance();
				// balance = service.showBalance(balance);
				System.out.println(accountBean.getCustomerBean().getFirstName()
						+ "\n" + accountBean.getCustomerBean().getLastName()
						+ "\n" + accountBean.getCustomerBean().getEmailId());
				accountBean.setDateOfRegistration(new Date());
				System.out.println("Your balance is: " + balance);

				break;

			case 3:

				// deposit

				System.out.println("Enter Account ID");
				accId = scanner.nextInt();
				System.out.println("Enter Mobile Number");
				mobileNumber = scanner.next();
				CustomerBean bean = new CustomerBean();
				bean = new CustomerBean();
				accountBean = service.findAccount(accId, mobileNumber);

				System.out.println("Enter amount that you want to deposit");
				double depositAmount = scanner.nextDouble();

				WalletTransactions wt = new WalletTransactions();
				wt.setTransactionType(1);
				wt.setTransactionDate(new Date());
				wt.setTransactionAmount(depositAmount);
				wt.setAccountBeanTo(null);

				accountBean.addTransation(wt);

				result = service.deposit(accountBean, depositAmount);

				if (result) {
					System.out
							.println("Successfully Deposited Money into Account ");
				} else {
					System.err.println("Not Deposited Money into Account ");
				}

				break;

			case 4:

				// withdraw

				System.out.println("Enter Account ID");
				accId = scanner.nextInt();
				System.out.println("Enter Mobile Number");
				mobileNumber = scanner.next();

				accountBean = service.findAccount(accId, mobileNumber);

				System.out.println("Enter amount that you want to withdraw");
				double withdrawAmt = scanner.nextDouble();

				wt = new WalletTransactions();
				wt.setTransactionType(2);
				wt.setTransactionDate(new Date());
				wt.setTransactionAmount(withdrawAmt);
				wt.setAccountBeanTo(null);

				accountBean.addTransation(wt);

				result = service.withdraw(accountBean, withdrawAmt);
				if (result) {
					System.out.println("Withdaw Money from Account done");
				} else {
					System.err.println("Money withdrawl is failed ");
				}

				break;

			case 5:

				// fund transfer

				System.out.println("Enter Account ID to Transfer Money From");
				int srcAccId = scanner.nextInt();
				System.out.println("Enter Mobile Number");
				mobileNumber = scanner.next();

				AccountBean accountBean1 = service.findAccount(srcAccId, null);

				System.out.println("Enter Account ID to Transfer Money to");
				int targetAccId = scanner.nextInt();

				AccountBean accountBean2 = service.findAccount(targetAccId,
						mobileNumber);

				System.out.println("Enter amount that you want to transfer");
				double transferAmt = scanner.nextDouble();

				wt = new WalletTransactions();
				wt.setTransactionType(3);
				wt.setTransactionDate(new Date());
				wt.setTransactionAmount(transferAmt);
				wt.setAccountBeanTo(accountBean2);

				accountBean1.addTransation(wt);

				result = service.fundTransfer(accountBean1, accountBean2,
						transferAmt);

				if (result) {
					System.out
							.println("Transfering Money from Account is done");
				} else {
					System.err
							.println("Transfering Money from Account Failed ");
				}

				break;

			case 6:

				// print transactions

				System.out
						.println("Enter your Account ID to print Transaction Details");
				accId = scanner.nextInt();
				System.out.println("Enter Mobile Number");
				mobileNumber = scanner.next();

				accountBean = service.findAccount(accId, mobileNumber);

				List<WalletTransactions> transactions = accountBean
						.getAllTransactions();

				System.out.println(accountBean);
				System.out.println(accountBean.getCustomerBean());

				for (WalletTransactions wt1 : transactions) {

					String str = "";
					if (wt1.getTransactionType() == 1) {
						str = str + "DEPOSIT";
					}
					if (wt1.getTransactionType() == 2) {
						str = str + "WITHDRAW";
					}
					if (wt1.getTransactionType() == 3) {
						str = str + "FUND TRANSFER";
					}

					str = str + "\t\t" + wt1.getTransactionDate();

					str = str + "\t\t" + wt1.getTransactionAmount();
					System.out.println(str);
				}

				break;
			case 7:
				System.exit(0);

				break;

			default:
				System.out.println("invalid option");
				break;
			}

			System.out.println("Do you want to continue press Y/N");
			ch = scanner.next().charAt(0);

		} while (ch == 'y' || ch == 'Y');

	}

}
