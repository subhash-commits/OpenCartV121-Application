package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(description = "Login with Valid Credentials",groups={"sanity","master"})
	public void loginTest() {
		logger.info("*****Started login Test*****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickmyaccount();
			logger.info("Clicked on Login link");
			hp.clickmylogin();
			logger.info("Enter Valid Credentials");
			LoginPage lp = new LoginPage(driver);
			lp.enterUsername(prop.getProperty("username"));
			lp.enterPassword(prop.getProperty("password"));
			lp.clickLogin();
			logger.info("Validate MyAccount Header Message in My Account Page");
			MyAccountPage mp = new MyAccountPage(driver);
			Assert.assertTrue(mp.isMyAccountPageExist());
		} catch (Exception e) {
			System.out.print(e.getMessage());
			Assert.fail();
		}
		logger.info("*****Finished login Test*****");
	}
}
