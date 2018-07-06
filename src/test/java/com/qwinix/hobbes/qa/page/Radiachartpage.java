package com.qwinix.hobbes.qa.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Radiachartpage {
	
	@FindBy(xpath="*//[name()='svg']*/[name()='g']*/[name()='path'][1]")
	public WebElement loginbutton;

}
