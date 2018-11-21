package com.capg.walletservice.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capg.walletservice.bean.AccountBean;
import com.capg.walletservice.bean.CustomerBean;
import com.capg.walletservice.service.AccountServiceImp;
import com.capg.walletservice.service.IAccountService;

public class AccountServiceImpTest {

	CustomerBean bean = new CustomerBean();
	AccountBean accbean = new AccountBean();
	public static IAccountService service = null;

	@BeforeClass
	public static void init() {
		service = new AccountServiceImp();

	}

	@Test(expected = Exception.class)
	public void testFirstNameLength() throws Exception {

		bean.setFirstName("Ram");
		bean.setLastName("Dasari");
		bean.setMobileNumber("8143227388");
		bean.setPanNum("BGDF12345A");
		bean.setEmailId("ramya1@gmail.com");
		bean.setAddress("Eluru");
		boolean result = service.createAccount(accbean, bean);
		assertFalse(result);
	}

	@Test(expected = Exception.class)
	public void testLastNameLength() throws Exception {

		bean.setFirstName("Ramya");
		bean.setLastName("hai");
		bean.setMobileNumber("8143227388");
		bean.setPanNum("BGDF12345A");
		bean.setEmailId("ramya1@gmail.com");
		bean.setAddress("Eluru");
		boolean result = service.createAccount(accbean, bean);
		assertFalse(result);
	}

	@Test(expected = Exception.class)
	public void testMobileNumberLength() throws Exception {

		bean.setFirstName("Ramya");
		bean.setLastName("Dasari");
		bean.setMobileNumber("814322");
		bean.setPanNum("BGDF12345A");
		bean.setEmailId("ramya1@gmail.com");
		bean.setAddress("Eluru");
		boolean result = service.createAccount(accbean, bean);
		assertFalse(result);
	}

	@Test(expected = Exception.class)
	public void testMobileNumberPattern() throws Exception {

		bean.setFirstName("Ramya");
		bean.setLastName("Dasari");
		bean.setMobileNumber("814322fgrgv");
		bean.setPanNum("BGDF12345A");
		bean.setEmailId("ramya1@gmail.com");
		bean.setAddress("Eluru");
		boolean result = service.createAccount(accbean, bean);
		assertFalse(result);
	}

	@Test(expected = Exception.class)
	public void testEmail() throws Exception {

		bean.setFirstName("Ramya");
		bean.setLastName("Dasari");
		bean.setMobileNumber("814322");
		bean.setPanNum("BGDF12345A");
		bean.setEmailId("ramya1gmail.com");
		bean.setAddress("Eluru");
		boolean result = service.createAccount(accbean, bean);
		assertFalse(result);
	}

	@Test(expected = Exception.class)
	public void testPanNumberLength() throws Exception {

		bean.setFirstName("Ramya");
		bean.setLastName("Dasari");
		bean.setMobileNumber("8143227388");
		bean.setPanNum("BG12345");
		bean.setEmailId("ramya1@gmail.com");
		bean.setAddress("Eluru");
		boolean result = service.createAccount(accbean, bean);
		assertFalse(result);
	}

	@Test(expected = Exception.class)
	public void testPanNumberPattern() throws Exception {

		bean.setFirstName("Ramya");
		bean.setLastName("Dasari");
		bean.setMobileNumber("8143227388");
		bean.setPanNum("123355675");
		bean.setEmailId("ramya1@gmail.com");
		bean.setAddress("Eluru");
		boolean result = service.createAccount(accbean, bean);
		assertFalse(result);
	}

	@Test(expected = Exception.class)
	public void testAddressForNull() throws Exception {

		bean.setFirstName("Ramya");
		bean.setLastName("Dasari");
		bean.setMobileNumber("8143227388");
		bean.setPanNum("BGDF12345A");
		bean.setEmailId("ramya1@gmail.com");
		bean.setAddress("");
		boolean result = service.createAccount(accbean, bean);
		assertFalse(result);
	}

	@Test
	public void testForPositive() throws Exception {

		bean.setFirstName("Ramya");
		bean.setLastName("Dasari");
		bean.setMobileNumber("8143227388");
		bean.setPanNum("BGDF12345A");
		bean.setEmailId("ramya1@gmail.com");
		bean.setAddress("Eluru");
		boolean result = service.createAccount(accbean, bean);
		assertTrue(result);
	}
	@Test
	public void testDeposit() throws Exception {

		bean.setFirstName("Ramya");
		bean.setLastName("Dasari");
		bean.setMobileNumber("8143227388");
		bean.setPanNum("CRGB12345H");
		bean.setEmailId("ramya@gmail.com");
		bean.setAddress("Eluru");
		boolean result = service.deposit(accbean, 1000.00);
		assertTrue(result);
	}
	
	@Test
	public void testWithdraw() throws Exception {

		bean.setFirstName("Ramya");
		bean.setLastName("Dasari");
		bean.setMobileNumber("8143227388");
		bean.setPanNum("CRGB12345H");
		bean.setEmailId("ramya@gmail.com");
		bean.setAddress("Eluru");
		boolean result = service.withdraw(accbean, 5000);
		assertTrue(result);
	}
	
	
	

}
