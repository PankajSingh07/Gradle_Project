package qa.automation.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {

	public static WebDriver driver;
	public static Properties properties;

	static String currentDir = System.getProperty("user.dir");
	String configFilePath = currentDir + File.separator + "src" + File.separator + "test" + File.separator + "resources"
			+ File.separator + "test.properties";

	static String chromeDriverPath = currentDir + File.separator + "driver" + File.separator + "chromedriver.exe";
	static String fireFoxDriverPath = currentDir + File.separator + "driver" + File.separator + "geckodriver.exe";

	public TestCase() {
		try {
			properties = new Properties();
			FileInputStream ip = new FileInputStream(configFilePath);
			properties.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void browserLaunch() {
		String browserName = properties.getProperty("browser");
		if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			// ChromeOptions chromeOptions = new ChromeOptions();
			// chromeOptions.addArguments("headless");
			// driver = new ChromeDriver(chromeOptions);

		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", fireFoxDriverPath);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equals("IE")) {

			System.setProperty("webdriver.ie.driver", chromeDriverPath);
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

		}
		driver.manage().window().maximize();
		driver.get(properties.getProperty("url"));
	}

	public static void browserQuit() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
