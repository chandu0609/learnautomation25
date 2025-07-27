package com.crm.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.ProductPage;
import com.crm.qa.util.TestUtil;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	ProductPage prodPage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeTest(alwaysRun = true)
	public void setUp(ITestContext context) {
		initialization(context);
		driver = (WebDriver) context.getAttribute("driver");
		loginPage = new LoginPage(driver);
		//context.setAttribute("driver", driver);
	}

	@Test(priority = 0, groups = "regression")
	public void loginPageTitle() {
		String title = loginPage.getPageTitle();
		Assert.assertEquals(title, "Let's Shop");
		
	}
	
	@Test(priority = 1,  groups = {"regression", "smoke"})
	public void registerBtnExists() {
		boolean regBtnDisplayed = loginPage.registerBtnExists();
		Assert.assertEquals(regBtnDisplayed, true);
	}
	
	@Test(priority = 2,  groups = "regression")
	public void userRegister() {
		System.out.println("test case user register");
	}
	
	
	@Test(priority = 3, groups = "smoke")
	//@Parameters({"username", "password"})
	public void loginUser() {//String username, String password) {
		
		  loginPage.inputUserName(prop.getProperty("username"));
		  loginPage.inputPassword(prop.getProperty("password"));
		 
			/*
			 * loginPage.inputUserName(username); loginPage.inputPassword(password);
			 */
		prodPage = loginPage.clickLoginBtn();
	}
	
	@AfterTest(alwaysRun = true)
	public void tearDown() throws IOException {
		
	}
}
