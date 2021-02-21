package utilities;

//import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class driverSetup extends GlobalVariables {
	private WebDriver driver;
	DesiredCapabilities capability = null;
	public static ExtentReports extent;
	private final String driversLocation = String.valueOf(Paths.get(projectPath,"/seleniumDrivers"));
	@BeforeSuite
	public void loadTestData(){
		System.out.println("LOADING EXCEL SHEET");
	}




	public WebDriver openBrowser(String browserType){

		switch (browserType.trim().toLowerCase()) {

			case "chrome":
				File f = new File(String.valueOf(Paths.get(driversLocation,"/ChromeDriverLatest.exe")));
				System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("test-type");
				chromeOptions.addArguments("no-sandbox");

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);

				chromeOptions.addArguments("--start-maximized");
				chromeOptions.addArguments("--start-maximized");
				chromeOptions.addArguments("test-type");
				chromeOptions.addArguments("ignore-certifcate-errors");
				chromeOptions.setExperimentalOption("prefs", prefs);
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();

				capabilities.setCapability("chrome.binary", String.valueOf(Paths.get(driversLocation,"/ChromeDriverLatest.exe")));
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

				driver = new ChromeDriver(capabilities);
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
break;
			case "edge":
				File f1 = new File(String.valueOf(Paths.get(driversLocation,"/msedgedriver.exe")));

				System.setProperty("webdriver.edge.driver", f1.getAbsolutePath());

				EdgeOptions edgeOptions = new EdgeOptions();
				DesiredCapabilities capabiliti = DesiredCapabilities.edge();
				capabiliti.setCapability("requireWindowFocus", true);
				capabiliti.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

				driver = new EdgeDriver(capabiliti);
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);

				break;
		}

		return driver;
	}

}
