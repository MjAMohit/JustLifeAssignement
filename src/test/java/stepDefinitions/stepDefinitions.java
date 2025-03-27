package stepDefinitions;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.FunctionLibrary;
import Utilities.SingletonWebDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class stepDefinitions {
	WebDriver driver;
	WebDriverWait wait;
	FunctionLibrary library = new FunctionLibrary();

	@Before
	public void setUp() {
		if (driver == null) {
			driver = SingletonWebDriver.getDriver();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
	}

	@Given("I am on the JustLife home cleaning services booking page")
	public void i_am_on_the_just_life_home_cleaning_services_booking_page() {
		try {
			driver.get("https://www.justlife.com/en-AE/home-cleaning/checkout/details?step=1");
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			library.waitForPageLoad(driver);
		} catch (Exception e) {
			Assert.fail("Unable to load home cleaning services page. Error ->" + e.getMessage());
		}
	}

	@Then("I select {string} hours of service by the profession")
	public void i_select_hours_of_service_by_the_profession(String hours) {
		String oHoursOfService = "//h4[contains(text(),'hours')]/parent::div//span[contains(text(),'%s')]";
		String hoursXpath = String.format(oHoursOfService, hours);

		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(hoursXpath)));
			element.click();
		} catch (Exception e) {
			Assert.fail("Unable to select " + hours + " hours. Error ->" + e.getMessage());
		}
	}

	@Then("I select {string} professional for service")
	public void i_select_professional_for_service(String professionals) {
		String oNumberOfProfessionals = "//h4[contains(text(),'professionals')]/parent::div//span[contains(text(),'%s')]";
		String professionalsXpath = String.format(oNumberOfProfessionals, professionals);
		try {
			WebElement element = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(professionalsXpath)));
			element.click();
		} catch (Exception e) {
			Assert.fail("Unable to select " + professionals + " professionals. Error ->" + e.getMessage());
		}
	}

	@Then("I choose {string} as material selection")
	public void i_choose_as_material_selection(String materials) {
		String oMaterialSelection = "//h4[contains(text(),'materials')]/parent::div//span[contains(text(),'%s')]";
		String materialXpath = String.format(oMaterialSelection, materials);
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(materialXpath)));
			element.click();
		} catch (Exception e) {
			Assert.fail("Unable to select " + materials + " as material selection. Error ->" + e.getMessage());
		}
	}

	@Then("I click on next step")
	public void i_click_on_next_step() {
		String oNextStep = "//span[contains(text(),'Next')]";
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oNextStep)));
			element.click();
		} catch (Exception e) {
			Assert.fail("Unable to click on Next step. Error ->" + e.getMessage());
		}
	}

	@Then("I skip popular add-ons")
	public void i_skip_popular_add_ons() {
		String oNextStep = "//span[contains(text(),'Next')]";
		try {
			Thread.sleep(5000);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oNextStep)));
			element.click();
		} catch (Exception e) {
			Assert.fail("Next Step Element not visible or interactable. Error ->" + e.getMessage());
		}
	}

	@Then("I choose suggested professional number {int}")
	public void i_choose_suggested_professional_number(int professionalNumber) {
		String oProfessional = "//img[@id='cleaner-%d']";
		oProfessional = String.format(oProfessional, (professionalNumber - 1));
		try {
			WebElement professional = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oProfessional)));
			professional.click();
		} catch (Exception e) {
			Assert.fail("Unable to select professional number " + professionalNumber);
		}
	}

	@Then("I choose the frequency as {string}")
	public void i_choose_the_frequency_as_and_professional(String frequency) {
		String oFrequency = "//h3[contains(text(),'%s')]";
		oFrequency = String.format(oFrequency, frequency);
		String oChooseFrequencyWindow = "//h4[contains(text(),'frequency')]";
		String oFrequencySelectButton = "//div[@id='frequency-button']";
		String oChangeFrequency = "//span[text()='Change']";
		try {
			WebElement frequencyWindow = driver.findElement(By.xpath(oChooseFrequencyWindow));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oFrequency))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oFrequencySelectButton))).click();
		} catch (NoSuchElementException e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oChangeFrequency))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oFrequency))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oFrequencySelectButton))).click();
		} catch (Exception e) {
			Assert.fail("Unable to choose frequency-" + frequency + ". Error->" + e.getMessage());
		}
	}

	@Then("I choose the {string} date")
	public void i_choose_the_date(String date) {
		String oFirstAvailableDate = "(//div[contains(@id,'date-picker')]//div[contains(@class,'selectable')])[1]";
		String oDate = "//span[text()='%s']";

		try {
			if (date.contains("available")) {
				WebElement dateElement = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(oFirstAvailableDate)));
				library.scrollBy("300", driver);
				dateElement.click();
			} else if (date.equalsIgnoreCase("Tomorrow")) {
				oDate = String.format(oDate, String.valueOf(library.tomorrowDate()));
				WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oDate)));
				dateElement.click();
			} else {
				oDate = String.format(oDate, date);
				WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oDate)));
				dateElement.click();
			}
		} catch (Exception e) {
			Assert.fail("Unable to select date" + date + ". Error ->" + e.getMessage());
		}

	}

	@Then("I choose {string} time")
	public void i_choose_time(String time) {

		String oFirstAvailableTime = "(//div[contains(@id,'time-picker')]//div[contains(@class,'selectable')])[1]";
		String oTime = "//span[contains(text(),'%s-')]";

		try {
			if (time.contains("available")) {
				WebElement timeElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oFirstAvailableTime)));
				timeElement.click();
			} else {
				oTime = String.format(oTime, time);
				WebElement timeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oTime)));
				timeElement.click();
			}
		} catch (Exception e) {
			Assert.fail("Unable to select the time -" + time + ". Error->" + e.getMessage());
		}

	}

	@Then("I enter phone number {string}")
	public void i_enter_phone_number(String phoneNumber) {
		String oPhoneNumber = "//input[contains(@id,'phone-number')]";
		String oContinueButton = "//button[contains(text(),'Continue')]";
		try {
			WebElement phoneNumberElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oPhoneNumber)));
			phoneNumberElement.sendKeys(phoneNumber);
			WebElement continueElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oContinueButton)));
			continueElement.click();
		} catch (Exception e) {
			Assert.fail("Check phone number. Error ->" + e.getMessage());
		}
	}

	@Then("I enter OTP {string}")
	public void i_enter_otp(String OTP) {
		String oOTPField = "//input[contains(@id,'otp')]";
		try {
			System.out.println("Entering OTP");
			List<WebElement> otpDigits = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(oOTPField)));
			for (int i = 0; i < otpDigits.size(); i++) {
				WebElement otpInput = otpDigits.get(i);
				wait.until(ExpectedConditions.elementToBeClickable(otpInput));
				otpInput.click();
				otpInput.sendKeys(String.valueOf(OTP.charAt(i)));
				System.out.println("Entered-" + OTP.charAt(i));
			}
		} catch (Exception e) {
			Assert.fail("Unable to enter OTP. Error ->" + e.getMessage());
		}
	}

	@Then("I check if login successful")
	public void i_check_if_login_successful() {
		try {
			String oUser = "//span[contains(@class,'user-name')]";
			WebElement user = driver.findElement(By.xpath(oUser));

			if (user.isDisplayed()) {
				System.out.println("Login successful!");
			} else {
				Assert.fail("Login failed! Incorrect OTP.");
			}
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Login failed! Check phone number and OTP.");
		}
	}

	@Then("I add {string} as Address if address window pops up")
	public void i_add_as_address_if_address_window_pops_up(String address) {
		String oAddressWindow = "//h4[contains(text(),'Address')]";
		String oInputField = "//input[@type='text']";
		String oAddressSearchResults = "//div[contains(@class,'search-results')]";
		String oNoResultsImage = "//img[@alt='SearchEmptyState']";
		String oFirstMatchingAddress = "(//div[contains(@id,'search-item')])[1]";
		String oLocation = "//span[contains(text(),'Location')]";

		try {
			WebElement addressWindow = driver.findElement(By.xpath(oAddressWindow));
			if (addressWindow.isDisplayed()) {
				WebElement inputField = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oInputField)));
				inputField.sendKeys(address);
				WebElement addressSearchResults = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oAddressSearchResults)));
				WebElement noResultImage = driver.findElement(By.xpath(oNoResultsImage));
				if (noResultImage.isDisplayed())
					Assert.fail("No results found for the address");
			}
		} catch (NoSuchElementException e) {
			WebElement firstMatchingAddress = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oFirstMatchingAddress)));
			firstMatchingAddress.click();
			WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oLocation)));
			confirm.click();
		} catch (Exception e) {
			Assert.fail("Unable to add address. Error ->" + e.getMessage());
		}
	}

	@Then("I select cash as payment method")
	public void i_select_cash_as_payment_method() {
		String oPaymentTypeButton = "//span[text()='Change']";
		String oCash = "//span[contains(text(),'Cash')]";
		String oSelectPayment = "//span[contains(text(),'Select payment')]";

		try {
			WebElement paymentButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(oPaymentTypeButton)));
			paymentButton.click();
			WebElement cash = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oCash)));
			cash.click();
			WebElement select = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(oSelectPayment)));
			select.click();
		} catch (Exception e) {
			Assert.fail("Unable to select cash payment as it is unavailable. Error-" + e.getMessage());
		}
	}

	@Then("I complete the booking")
	public void i_complete_the_booking() {
		String oComplete = "//span[text()='Complete']";

		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oComplete)));
			element.click();
		} catch (Exception e) {
			Assert.fail("Unable to complete booking. Error ->" + e.getMessage());
		}
	}

	@Then("check if order is placed")
	public void check_if_order_is_placed() {
		String oOrder = "//*[contains(text(),'Order Placed')]";

		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oOrder)));
			if (element.isDisplayed())
				System.out.println("Order Placed");
		} catch (Exception e) {
			Assert.fail("Unable to place Order!");
		}
	}

	@AfterStep
	public void afterScenario(Scenario scenario) {
		library.waitForPageLoad(driver);
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", scenario.getName());
	}

	@After
	public void close() {
		if (driver != null)
			driver.quit();
	}
}
