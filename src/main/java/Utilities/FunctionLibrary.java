package Utilities;

import java.time.Duration;
import java.time.LocalDate;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FunctionLibrary {
	public int tomorrowDate() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		int tomorrowDay = tomorrow.getDayOfMonth();
		return tomorrowDay;
	}
	
	public void scrollBy(String pixels,WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, "+pixels+")");
	}
	
	public void scrollToElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void waitForPageLoad(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}
}
