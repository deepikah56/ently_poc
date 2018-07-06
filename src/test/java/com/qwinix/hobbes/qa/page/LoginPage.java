package com.qwinix.hobbes.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.qwinix.hobbes.qa.BaseDriver;


public class LoginPage {
	WebDriver driver = BaseDriver.driver;

	public LoginPage() 
	{
		PageFactory.initElements(BaseDriver.driver, this);
	}
	
	@FindBy(xpath="//button[text()='Login']")
	public WebElement loginbutton;

	@FindBy(xpath="//input[@name='email']")
	public WebElement email;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement password;
	

	@FindBy(xpath="//button[@class='auth0-lock-submit']")
	public WebElement submit;
	

	@FindBy(xpath="//h3[text()='Repositories and Commits:']")
	public WebElement textverify;
	
	@FindBy(xpath="//div[@class='hintStyle']")
	public WebElement getdata;
	
	
	
	public void onMouseOver(WebElement we)
	{
		Actions action = new Actions(driver);
		action.moveToElement(we).perform();
//		action.click().perform();
	}
	
	@FindBy(xpath="//button[@class='backbtn btn btn-primary']")
	public WebElement backbutton;
	}
	

