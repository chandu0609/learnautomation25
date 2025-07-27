package com.crm.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.ProductPage;

public class ProductsTest extends TestBase {

	LoginPage loginPage;
	ProductPage prodPage;
	
	public ProductsTest() {
		super();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setup(ITestContext context) {
		initialization(context);
		
		driver = (WebDriver) context.getAttribute("driver");
		loginPage = new LoginPage(driver);
		loginPage.inputUserName(prop.getProperty("username"));
		loginPage.inputPassword(prop.getProperty("password"));
		prodPage = loginPage.clickLoginBtn();
	}
	
	@Test(groups="smoke")
	public void verifyWebText() {
		boolean imageVisible = prodPage.imageMenuDisplayed();
		Assert.assertEquals(imageVisible, true);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		//driver.quit();
	}
}
