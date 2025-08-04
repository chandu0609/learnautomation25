package com.crm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class RegisterPage extends TestBase {
	
	@FindBy(id = "firstName")
	WebElement firstNameTxt;
	
	@FindBy(id = "lastName")
	WebElement lastNameTxt;
	
	@FindBy(id = "userEmail")
	WebElement emailTxt;
	
	@FindBy(id = "userMobile")
	WebElement mobileTxt;
	
	@FindBy(xpath ="//select[@formcontrolname='occupation']")
	WebElement occupationTxt;
	
	@FindBy(id = "userPassword")
	WebElement passwordTxt;
	
	@FindBy(id = "confirmPassword")
	WebElement confirmPasswordTxt;
	
	@FindBy(xpath = "//input[@type='checkbox' and @formcontrolname='required']")
	WebElement adultConsent;
	
	public RegisterPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void fillInRegisterForm(String firstName, String lastName, String email, String phNo, String occupation, String gender, String password) throws InterruptedException {
		firstNameTxt.sendKeys(firstName);
		lastNameTxt.sendKeys(lastName);
		emailTxt.sendKeys(email);
		mobileTxt.sendKeys(phNo);
		
		Select dropDownGender = new Select(occupationTxt);
		dropDownGender.selectByVisibleText(occupation);
		
		 WebElement radio = driver.findElement(By.xpath("//label[text()='Gender']/following-sibling::label/input[@type='radio' and @value='"+gender+"']"));
		 WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 eWait.until(ExpectedConditions.elementToBeClickable(radio));
		 radio.click();
		 
		 passwordTxt.sendKeys(password);
		 confirmPasswordTxt.sendKeys(password);
		
		 eWait.until(ExpectedConditions.elementToBeClickable(adultConsent));
		 adultConsent.click();
		 Thread.sleep(5000);
	}

}
