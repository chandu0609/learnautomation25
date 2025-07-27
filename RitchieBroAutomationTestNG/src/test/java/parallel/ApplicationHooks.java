package parallel;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.factory.DriverFactory;
import com.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
	
	
	private Properties prop;
	private ConfigReader cReader;
	private DriverFactory driverFactory;
	private WebDriver driver;
	
	
	@Before (order = 0)
	public void initalizeProp() {
		cReader = new ConfigReader();
		prop = cReader.init_Prop();
	}
	
	@Before (order = 1)
	public void initalizeDriver() {
		String browser = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_Driver(browser);
	}
	
	
	@After (order = 1)
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			String sShot = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", sShot);
		}
	}
	
	@After (order = 0)
	public void quitBrowser() {
		driver.quit();
	}

}
