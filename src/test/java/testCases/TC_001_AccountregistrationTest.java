package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountregistrationTest extends BaseClass {
	@Test(description="Account Registration test",groups={"regression","master"})
	public void Verify_accountRegistrationTest() {


		logger.info("*******Starting TC001 - Account Registration Test*******");
		try {
		// object for homepage,driver to interact with elements
		HomePage hp = new HomePage(driver);
		hp.clickmyaccount();
		logger.info("Clicked on myaccount link");
		hp.clickmyrgister();
		logger.info("Clicked on register link");

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Providing Customer Details");
		regpage.enterFirstName(randomString().toUpperCase());
		regpage.enterLastName(randomString().toUpperCase());
		regpage.enterEmail(randomString() + "@gmail.com");
		regpage.enterTelePhoneNumber(randomNumber());
		String passwordtxt = randomalphNumeric();
		regpage.enterPassword(passwordtxt);
		regpage.enterconfirmPassword(passwordtxt);
		regpage.clickpolicy();
		regpage.clickContinue();

		logger.info("Validating Expected Message");
		String message = regpage.getConfirmationmesssage();
		System.out.println(message);
		Assert.assertEquals("Your Account Has Been Created!", message);
		}catch(Exception e) {
			logger.error("Test Failed");
			logger.error("Debug logs...");
			Assert.fail();
		}
		logger.info("*******Finished TC001 - Account Registration Test*******");
	}

	
}
