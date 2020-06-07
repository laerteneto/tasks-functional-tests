package br.ce.laerte.tasks.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};

	// Used to remote executions in parallel
	public static WebDriver initDriver() {
		WebDriver driver = null;
		ChromeOptions cap = new ChromeOptions();
		// cap.setHeadless(true);
		try {
			driver = new RemoteWebDriver(new URL("http://172.17.0.1:4444/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	public static WebDriver getDriver() {
		return threadDriver.get();
	}

	public static void killDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
		}
		if (threadDriver != null) {
			threadDriver.remove();
		}
	}

}
