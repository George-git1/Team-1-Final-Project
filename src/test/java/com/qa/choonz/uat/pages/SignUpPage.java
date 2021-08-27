package com.qa.choonz.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage {

	public final String url = "http://127.0.0.1:5500/static/index.html";
	// CLICK LOG IN/SIGN UP BUTTON
	@FindBy(xpath = "/html/body/nav/div[2]/ul[1]/form/div[1]/li/div/button")
	private WebElement signUpBtn;
	// CLICKS OPTION TO MAKE AN ACCOUNT
	@FindBy(xpath = "/html/body/nav/div[2]/ul[1]/form/div[1]/li/div/div/button[2]")
	private WebElement createAccBtn;
	// FINDS YOUR NAME SECTION
	@FindBy(xpath = "/html/body/div[5]/div/div/div[2]/form/div[1]/input")
	private WebElement fullNameField;
	// FINDS USERNAME SECTION
	@FindBy(xpath = "/html/body/div[5]/div/div/div[2]/form/div[2]/input")
	private WebElement userNameField;
	// FINDS PASSWORD SECTION
	@FindBy(xpath = "/html/body/div[5]/div/div/div[2]/form/div[3]/input")
	private WebElement passwordField;
	// FINDS CREATE BUTTON TO MAKE ACCOUNT
	@FindBy(xpath = "/html/body/div[5]/div/div/div[2]/form/div[4]/button")
	private WebElement createBtn;
	// FINDS TEXT SAYING CREATED
	@FindBy(xpath="//*[@id=\"success-create\"]")
	private WebElement textBox;
	
	public SignUpPage addFullname(String full_name) {
		fullNameField.sendKeys(full_name);
		return this;
	}

	public SignUpPage addUsername(String user_name) {
		userNameField.sendKeys(user_name);
		return this;
	}

	public SignUpPage addPassword(String password) {
		passwordField.sendKeys(password);
		return this;
	}
	
	public SignUpPage clickSignUp() {
		signUpBtn.click();
		createAccBtn.click();
		return this;
	}
	
	public SignUpPage clickCreate() {
		createAccBtn.click();
		return this;
	}
	
	public SignUpPage clickCreateAccBtn() {
		createBtn.click();
		return this;
	}
	
	public String getText() {
		return textBox.getText();
		}

}
