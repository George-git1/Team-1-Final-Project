package com.qa.choonz.utilities;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
	/**
	 * Returns a web driver based on the <b>browser<b> system property.<br>
	 * <br>
	 * Pass it in with: <code>mvn -Dbrowser=chrome</code>
	 * 
	 * @return WebDriver
	 * @throws Exception
	 */
	public static WebDriver getDriver() throws Exception {
		String webDriver = System.getProperty("browser", "chrome");

		switch (webDriver.toUpperCase()) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			return new ChromeDriver();
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
			return new FirefoxDriver();
		case "EDGE":
			System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/edgedriver.exe");
			return new EdgeDriver();
		default:
			throw new Exception("Fatal: No browser property supplied - Could not default to chromedriver, no driver available");
		}
	}
	
	/**
	 * Returns a web driver based on the <b>browser<b> system property, options set to desired capabilities.<br>
	 * <br>
	 * Pass it in with: <code>mvn -Dbrowser=chrome</code><br>
	 * 
	 * @param Capabilities
	 * @return WebDriver
	 * @throws Exception
	 */
	public static WebDriver getDriver(Capabilities capabilities) throws Exception {
		String webDriver = System.getProperty("browser", "chrome");

		switch (webDriver.toUpperCase()) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			return new ChromeDriver();
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
			return new FirefoxDriver();
		case "EDGE":
			System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/edgedriver.exe");
			return new EdgeDriver();
		default:
			throw new Exception("Fatal: No browser property supplied - Could not default to chromedriver, no driver available");
		}
	}
}

