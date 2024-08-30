package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-firstname")
	WebElement firstname;

	@FindBy(id = "input-lastname")
	WebElement lastname;

	@FindBy(id = "input-email")
	WebElement email;

	@FindBy(id = "input-telephone")
	WebElement telephone;

	@FindBy(id = "input-password")
	WebElement password;

	@FindBy(id = "input-confirm")
	WebElement confirmpassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement policycheckbox;

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	WebElement continuebtn;

	@FindBy(xpath = "//div[@id='content']//h1[text()='Your Account Has Been Created!']")
	WebElement confirmationmessage;

	public void enterFirstName(String firstName) {
		firstname.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		lastname.sendKeys(lastName);
	}

	public void enterEmail(String emailaddress) {
		email.sendKeys(emailaddress);
	}

	public void enterTelePhoneNumber(String telephoneno) {
		telephone.sendKeys(telephoneno);
	}

	public void enterPassword(String userpassword) {
		password.sendKeys(userpassword);
	}

	public void enterconfirmPassword(String confirmuserpassword) {
		confirmpassword.sendKeys(confirmuserpassword);
	}

	public void clickpolicy() {
		policycheckbox.click();

	}

	public void clickContinue() {
		continuebtn.click();

	}

	public String getConfirmationmesssage() {
		try {
			return confirmationmessage.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
