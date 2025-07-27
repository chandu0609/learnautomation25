package com.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class HomePage {
	
	private WebDriver driver;
	private WebDriverWait eWait; 

	public HomePage(WebDriver driver) {
		this.driver = driver;
	    eWait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	//Locators
	
	private By searchTxtBox = By.id("simple-keyword-search");
	private By searchEquipmentTxtBox = By.id("keyword-submit");
	private By browseByCategoryMenuOption = By.id("Browse-by-category");
	
	//User operation methods
	
	public void setEquipmentOnTextBox(String searchString) {
		eWait.until(ExpectedConditions.elementToBeClickable(searchTxtBox));
		driver.findElement(searchTxtBox).sendKeys(searchString);
	}
	
	public void clickSearchEquipment() {
		driver.findElement(searchEquipmentTxtBox).click();
		
	}
	
	public SearchResultPage searchEquipment(String searchString) {
		eWait.until(ExpectedConditions.elementToBeClickable(searchTxtBox));
		driver.findElement(searchTxtBox).sendKeys(searchString);
		driver.findElement(searchEquipmentTxtBox).click();
		return new SearchResultPage(driver);
	}

	public String getTitleOfRBAuction() {
		return driver.getTitle();
	}

	public void clickOnMenuOption(String menuOption) {
		driver.findElement(browseByCategoryMenuOption).click();
	}

	public List<String> getListOfActualSubMenuItems() {
		 By subMenuList = By.xpath("//span[@id='Browse-by-category']/..//aside/ul[1]//li");
		 List<WebElement> listMenu = driver.findElements(subMenuList);
		 List<String> subMenuOptionsList = new ArrayList<String>(); 
		 for (WebElement webElement : listMenu) {
			 String subMenuOptionName = webElement.findElement(By.xpath("span")).getText(); //pick up first span child for text;
			 System.out.println(subMenuOptionName);
			 subMenuOptionsList.add(subMenuOptionName);
		}
		 return subMenuOptionsList;		
	}


	

}
