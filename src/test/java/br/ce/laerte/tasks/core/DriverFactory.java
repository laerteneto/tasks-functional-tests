package br.ce.laerte.tasks.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	private static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {
			// createDriver();
			createRemoteDriver();

		}
		return driver;
	}

	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static void createDriver() {
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
		driver = new ChromeDriver(options);
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void createRemoteDriver() {
		// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
		// DesiredCapabilities cap = DesiredCapabilities.chrome(); // deprecated
		ChromeOptions cap = new ChromeOptions();
		cap.setHeadless(true);
		try {
			driver = new RemoteWebDriver(new URL("http://172.17.0.1:4444/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.navigate().to("http://192.168.0.178:8001/tasks/");
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

}
