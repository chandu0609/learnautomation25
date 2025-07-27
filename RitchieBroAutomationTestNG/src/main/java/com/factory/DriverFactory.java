package com.factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	private WebDriver driver;
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	public WebDriver init_Driver(String browser) {
	
		if(browser.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = WebDriverManager.chromedriver().capabilities(options).create();
			tlDriver.set(driver);
		}
		else if(browser.equals("firefox")) {
			driver = WebDriverManager.firefoxdriver().create();
			tlDriver.set(driver);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}
	
	public static synchronized WebDriver getDriver() {
			return tlDriver.get();
	}

}
