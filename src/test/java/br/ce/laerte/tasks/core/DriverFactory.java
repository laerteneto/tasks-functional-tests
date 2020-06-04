package br.ce.laerte.tasks.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
	private static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null)
			createDriver();

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
}
