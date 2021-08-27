package com.qa.choonz.hooks;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class SeleniumHooks {

	private WebDriver driver;
	
	@Before("@webpage")
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	
	@After("@webpage")
	public void teardown(Scenario scenario) {
	
		scenario.attach(takeScreenshot(), "image/png", scenario.getName());
		
		this.driver.quit();
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	private byte[] takeScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}


}
