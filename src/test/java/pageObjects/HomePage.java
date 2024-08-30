package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[text()='My Account']")
	WebElement myAccount;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Register']")
	WebElement register;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//li//a[text()='Login']")
	WebElement login;

	public void clickmyaccount() {
		myAccount.click();
	}

	public void clickmyrgister() {
		register.click();
	}
	public void clickmylogin() {
		login.click();
	}
}
