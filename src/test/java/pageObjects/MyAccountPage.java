package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='content']//h2[text()='My Account']")
	WebElement myaccountmsgheader;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//li//a[text()='Logout']")
	WebElement logout;

	public boolean isMyAccountPageExist() {
		try {
			return myaccountmsgheader.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void mylogout() {
		logout.click();
	}
}
