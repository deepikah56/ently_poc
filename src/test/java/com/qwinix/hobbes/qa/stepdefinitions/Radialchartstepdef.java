package com.qwinix.hobbes.qa.stepdefinitions;

import com.qwinix.hobbes.qa.steps.RadialchartStep;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Radialchartstepdef {
	RadialchartStep objRd = new RadialchartStep();
	LoginSteps objLP = new LoginSteps();
	
	@Given("^I am on Dashboard page$")
	public void i_am_on_Dashboard_page() throws Throwable {
		objLP.openurl();
		objLP.login();
		Thread.sleep(2000);
		
	}

	@When("^I navigate on the radial chart$")
	public void i_navigate_on_the_radial_chart() throws Throwable {
		objRd.RadialAccessToken();
	}

	@Then("^I should able to fetch all the data in radial cha$")
	public void i_should_able_to_fetch_all_the_data_in_radial_cha() throws Throwable {
	    objRd.fetchdata();
	}



}
