package com.crm.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.RegisterPage;
import com.crm.qa.util.TestUtil;

public class RegisterTest extends TestBase{
	
	 RegisterPage registerPage;
	 LoginPage loginPage;
	 String testDatasheet = "users";
	 
	 public RegisterTest() {
		 super();
	 }
	 
	 @BeforeMethod
	 public void setup(ITestContext context) {
		 initialization(context);
			driver = (WebDriver) context.getAttribute("driver");
		 loginPage = new LoginPage(driver);
		 
	 }
	 
	 @DataProvider
	 public Object[][] getUserTestData() {
		 Object [][] data = TestUtil.getTestData(testDatasheet);
		 return data;
	 }
	 
	 @Test(priority = 0, dataProvider =  "getUserTestData" )
	 public void RegisterUser(String firstname, String lastname, String email, String phonenumber, String role, String gender, String password) {
		 registerPage = loginPage.clickRegisterBtn();
		 try {
			registerPage.fillInRegisterForm(firstname, lastname, email, phonenumber, role, gender, password);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 @AfterMethod
	 public void tearDown() {
		 driver.quit();
	 }

}
