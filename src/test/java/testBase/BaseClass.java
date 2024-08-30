package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

	// Loading config.properties file
	public Logger logger;// for log4j
	public static WebDriver driver;// to keep same for every class(if its non static every time BaseClass() object
									// created one copy of driver get created)
	public Properties prop;
	public FileInputStream fin;

	@BeforeClass(groups = { "sanity", "regression", "master" })
	@Parameters({ "OS", "Browser" })
	public void setup(@Optional("windows") String os, @Optional("chrome") String Browser) throws IOException {
		fin = new FileInputStream("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(fin);
		logger = LogManager.getLogger(this.getClass());

		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();

			// OS
			if (os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching OS");
				return;
			}

			// Browser
			switch (Browser) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("Invalid Browser Name");
				return;
			}
			 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		}
		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (Browser) {
			case "chrome":
				driver = new ChromeDriver();
				logger.info("Launching Chrome Browser");
				break;
			case "edge":
				driver = new EdgeDriver();
				logger.info("Launching Edge Browser");
				break;
			default:
				logger.info("Invalid Browser Name");
				System.out.println("Invalid Browser Name");
				return;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(prop.getProperty("appUrl"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "sanity", "regression", "master" })
	public void tearDown() throws IOException {
		fin.close();
		driver.quit();
	}

	public String randomString() {
		return RandomStringUtils.randomAlphabetic(8);

	}

	public String randomNumber() {
		return RandomStringUtils.randomNumeric(10);

	}

	public String randomalphNumeric() {
		return RandomStringUtils.randomAlphanumeric(8);

	}

	public String captureScreen(String tname) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destpath = System.getProperty("user.dir") + ".\\screenshots\\" + tname + "_" + timestamp + ".png";
		File dest = new File(destpath);
		FileUtils.copyFile(src, dest);
		return destpath;
	}
}
