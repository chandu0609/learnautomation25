package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(WebEventListener.class)
public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static List<WebDriver> driverPool = new ArrayList<>();
	
	public TestBase() {
		
		try {
			prop = new Properties();
			FileInputStream fs = new FileInputStream(
                        System.getProperty("user.dir")+"/src/main/java/com/crm/qa/config/config.properties");
		    prop.load(fs);
		
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void initialization(ITestContext context) {
		String browserName = System.getProperty("browser", prop.getProperty("browser"));
		String setheadless = prop.getProperty("headless");
		
		boolean isHeadless = setheadless.equalsIgnoreCase("true");
		WebDriver baseDriver = null;
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if(isHeadless)
			{
				options.addArguments("--headless");
				options.addArguments("--disable-gpu");
				options.addArguments("--window-size=1920,1080");
			}
			baseDriver = new ChromeDriver(options);
			System.out.println("LAUNCHED CHROME");
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			 WebDriverManager.firefoxdriver().setup();
			 FirefoxOptions options = new FirefoxOptions();
		        if (isHeadless) {
		            options.addArguments("--headless");
		        }
		        baseDriver = new FirefoxDriver(options);
		        System.out.println("LAUNCHED FIREFOX");
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			if(isHeadless) {
				EdgeOptions options = new EdgeOptions();
				if(isHeadless) {
					options.addArguments("--headless");
					options.addArguments("disable-gpu");
				}
				baseDriver = new EdgeDriver(options);
				System.out.println("LAUNCHED EDGE");
			}
		}
		else {
	        throw new RuntimeException("Unsupported browser: " + browserName);
	    }
		
	
		WebDriver decoratedDriver = new EventFiringDecorator(new WebEventListener()).decorate(baseDriver);
		driver = decoratedDriver;
		context.setAttribute("driver", driver);
		driverPool.add(driver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.PAGE_IMPLICT_WAIT));
		
		driver.get(prop.getProperty("url"));
	}
	
	  @AfterSuite(alwaysRun = true)
	    public void cleanUp() {
	       for (WebDriver driver : driverPool) {
	    	   driver.quit();
	   	}
	  }

}
