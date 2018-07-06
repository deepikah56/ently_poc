package com.qwinix.hobbes.qa.steps;

import com.qwinix.hobbes.qa.stepdefinitions.LoginSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Loginstepdef {
	LoginSteps objLP = new LoginSteps();

	@Given("^I am on React login page$")
	public void i_am_on_React_login_page() throws Throwable {
	  objLP.openurl();
	  objLP.login();
	}

	@When("^I enter email and parssword credential$")
	public void i_enter_email_and_parssword_credential() throws Throwable {
	    objLP.access_token();
	}

	@Then("^I should see the React home page$")
	public void i_should_see_the_React_home_page() throws Throwable {
		objLP.fetchdata();
	   
	}
	


}
