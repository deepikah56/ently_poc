package com.qwinix.hobbes.qa;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	format = {"json:target/cucumber.json","html:target/site/cucumber-pretty"},
	features= {"src/test/resources/Features"},
	glue= {"com.qwinix.hobbes.qa"}, tags="@RadialGraph"
)


public class Cuckes {

}
