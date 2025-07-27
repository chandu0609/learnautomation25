package com.crm.qa.pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class ProductPage extends TestBase{
	
	
	private WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(20));
	@FindBy(xpath = "//div[contains(text(),'Showing')]")
	WebElement imageMenu;
	
	public ProductPage() {
		PageFactory.initElements(driver, this);
	}
	public boolean imageMenuDisplayed() {
		try {
	    eWait.until(ExpectedConditions.visibilityOf(imageMenu));
		boolean t=  imageMenu.isDisplayed();
		return t;
		}
		catch (NoSuchElementException | StaleElementReferenceException e) {}
		return false;
	}
	
	
}
