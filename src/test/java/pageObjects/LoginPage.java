package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement usernamefield;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement passwordfield;

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	WebElement login;

	public void enterUsername(String email) {
		usernamefield.sendKeys(email);
	}

	public void enterPassword(String password) {
		passwordfield.sendKeys(password);
	}

	public void clickLogin() {
		login.click();
	}

}
