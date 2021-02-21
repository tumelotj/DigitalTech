package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ObjectRepository;

import java.util.Properties;
import java.util.Random;

public class pageObjects {
	private static final int TIMEOUT = 5;
	private static final int POLLING = 100;
	private static Properties OR;
	protected WebDriver driver;
	private WebDriverWait wait;
	ObjectRepository objectRepository;

	public pageObjects(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, TIMEOUT, POLLING);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);


	}


	public void navigate(String URL) {
		driver.get(URL);
		driver.manage().window().maximize();
	}


	public void waitForElementToAppear(String locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}


	public void click(String locator) {
		try {
			driver.findElement(By.xpath(locator)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void input(String locator, String data) {
		try {
			driver.findElement(By.xpath(locator)).sendKeys(Keys.chord(Keys.CONTROL, "a"), data, Keys.TAB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void select(String locator, String data) {
		try {
			Select dropdown = new Select(driver.findElement(By.xpath(locator)));
			dropdown.selectByVisibleText(data);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static String generateCellphone() {
		int min = 1;
		int max = 9;
		boolean firstNumGenerated = false;
		StringBuilder number = new StringBuilder();
		StringBuilder cellNum = new StringBuilder("083");
		Random rand = new Random();
		for (int i = 1; i <= 7;i++) {
			number.append(rand.nextInt((max - min) + 1) + min);

			if (number.length()==3&&!firstNumGenerated) {
				cellNum.append(" ").append(number);
				number = new StringBuilder();
				firstNumGenerated = true;
			}else if(number.length()==4){
				cellNum.append(" ").append(number);
				break;
			}

		}
		return cellNum.toString();
	}
}
