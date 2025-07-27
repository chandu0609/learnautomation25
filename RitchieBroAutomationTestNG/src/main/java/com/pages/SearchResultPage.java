package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {

	private WebDriver driver;
	private WebDriverWait eWait;
	
	/*By Locators*/
	private By searchResultsLabel = By.xpath("//div[@id='lblShowResultsFor']/p[contains(text(),'Showing')]");
	private By firstResultValue = By.xpath("//div[@id='searchResultsList']//div[1]//h3");
	private By minYearTxtBox = By.xpath("//div[@id='manufacturer_year_dt']//input[1]");
	private By maxYearTxtBox = By.xpath("//div[@id='manufacturer_year_dt']//input[2]");
	
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
	    eWait = new WebDriverWait(driver, Duration.ofSeconds(20));
}

	public String getResultsCount() {
		String resultsCountString = driver.findElement(searchResultsLabel).getText();
		return resultsCountString;
		
	}

	public String getFirstResultItem() {
		String firstResultFullName = driver.findElement(firstResultValue).getText();
		return firstResultFullName;
		
	}

	public void setMinYear(String minYr) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(minYearTxtBox));
		((JavascriptExecutor) driver).executeScript("arguments[0].value='';", driver.findElement(minYearTxtBox));
		driver.findElement(minYearTxtBox).sendKeys(minYr);
	}

	public void setMaxYear(String maxYr) throws InterruptedException {
		Thread.sleep(5000); //Waiting for page load to update result
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(maxYearTxtBox)); //Move to the element
		((JavascriptExecutor) driver).executeScript("arguments[0].value='';", driver.findElement(maxYearTxtBox));  //Remove using JavaScript Executor
		driver.findElement(maxYearTxtBox).sendKeys(maxYr);
		Thread.sleep(5000); //Waiting for page load to update result
		
	}
	
	

}
