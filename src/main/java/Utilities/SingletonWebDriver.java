package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SingletonWebDriver {
	private static WebDriver driver;
	private SingletonWebDriver() {}

	public static WebDriver getDriver() {
		if (driver == null) {
			String path=System.getProperty("user.dir")+"\\resources\\Drivers\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
