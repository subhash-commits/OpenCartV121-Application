package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Valid Data - login success - test pass - logout
 * Valid Data - login fail - test fail 
 * 
 * InValid Data - login success - test pass - logout
 * InValid Data - login fail - test pass 
 */
public class TC_003_LoginTestDDT extends BaseClass {

	@Test(dataProvider = "LoginData",dataProviderClass=DataProviders.class,groups="dataDriven")//getting data from different class
	public void DataDrivenloginTest(String Username, String Password, String result) {
		logger.info("*****Started - TC_003_LoginTestDDT - Data Driven login Test*****");
		try {
			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickmyaccount();
			logger.info("Clicked on Login link");
			hp.clickmylogin();
			logger.info("Enter the Credentials");
			// loginPage
			LoginPage lp = new LoginPage(driver);
			lp.enterUsername(Username);
			lp.enterPassword(Password);
			lp.clickLogin();
			logger.info("Validate MyAccount Header Message in My Account Page");
			// MyAccountPage
			MyAccountPage mp = new MyAccountPage(driver);
			boolean value = mp.isMyAccountPageExist();
			if (result.equals("valid") && value == true) {
				hp.clickmyaccount();
				mp.mylogout();
				 Assert.assertTrue(true);
			} else if (result.equals("valid") && value == false) {
                Assert.assertTrue(false);
			} else if (result.equals("invalid") && value == true) {
				 hp.clickmyaccount();
				 mp.mylogout();
				 Assert.assertTrue(false);
			} else if (result.equals("invalid") && value == false) {
				 Assert.assertTrue(true);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());

		}
		logger.info("*****Finished TC_003_LoginTestDDT Test*****");
	}
}
