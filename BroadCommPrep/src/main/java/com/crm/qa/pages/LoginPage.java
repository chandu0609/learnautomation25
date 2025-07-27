package com.crm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
   @FindBy(xpath ="//a[@routerlink='/auth/register']")
   WebElement registerBtn;

   @FindBy(id="userEmail")
   WebElement userNameTxt;
   
   @FindBy(xpath = "//input[@formcontrolname='userPassword']")
   WebElement passwordTxt;
   
   @FindBy(xpath ="//input[contains(@class,'login-btn')]")
   WebElement loginBtn;
   
   public LoginPage(WebDriver driver) {
	    PageFactory.initElements(driver, this);
   }
   
   public String getPageTitle() {
	   return driver.getTitle();
   }
   
   public boolean registerBtnExists() {
	   return registerBtn.isDisplayed();
   }
   
   public void inputUserName(String userName) {
	   userNameTxt.sendKeys(userName);
   }
	
   public void inputPassword(String pwd) {
	   passwordTxt.sendKeys(pwd);
   }
   
   public ProductPage clickLoginBtn() {
	   loginBtn.click();
	   return new ProductPage();
   }
   
   public RegisterPage clickRegisterBtn() {
	   registerBtn.click();
	   return new RegisterPage();
   }
}

