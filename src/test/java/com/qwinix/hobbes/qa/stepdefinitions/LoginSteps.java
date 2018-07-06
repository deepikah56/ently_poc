package com.qwinix.hobbes.qa.stepdefinitions;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qwinix.hobbes.qa.BaseDriver;
import com.qwinix.hobbes.qa.page.LoginPage;
import com.qwinix.hobbes.qa.steps.ConfigReader;
import com.qwinix.hobbes.qa.steps.Jsonobject;
public class LoginSteps {

public static JavascriptExecutor js;
WebDriver driver = BaseDriver.driver;
ConfigReader config = new ConfigReader();
LoginPage objLP = new  LoginPage();
ArrayList<Jsonobject> arrayoflist = new ArrayList<>();


public void openurl() {
		driver.get(config.getUrl());
		driver.manage().window().maximize();
		
}

public void login() {
   objLP.loginbutton.click();
   objLP.email.sendKeys("qwinix@yopmail.com");
   objLP.password.sendKeys("Qwinix123!");
   objLP.submit.click();
}


public void access_token() {
	String actual = driver.findElement(By.xpath("//h3[text()='Repositories and Commits:']")).getText();
	System.out.println("actualvalue is = "+actual);
	Assert.assertEquals("Repositories and Commits:", actual);
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	Object test= (js.executeScript("return localStorage.getItem('access_token')"));
	String token_id = (test).toString();
	String email = ("qwinix@yopmail.com").toString();
	
	Response repoNcommits = RestAssured.given().baseUri("http://gitviz.qwinix.net:8000").basePath(" singleRepoNCommits/q-dash").header("Authorization","Bearer "+token_id)
	.contentType("application/json").log().body().
	when().get();
	String jsonobject = repoNcommits.asString();
	System.out.println(jsonobject);
	JSONObject object = new JSONObject(jsonobject);
	try
	{
		JSONArray repos = object.getJSONArray("singleRepoNCommits");
		for(int i=0;i<repos.length();i++)
		{
			
			Jsonobject jObject = new Jsonobject();
			JSONObject lj=repos.getJSONObject(i);
			jObject.setXval(lj.getString("x"));
			jObject.setYval(lj.getInt("y"));
			arrayoflist.add(jObject);
		}
	} catch (JSONException e)
	{
		
		e.printStackTrace();
	}
	}
	


public void fetchdata() throws InterruptedException {
	  int sizeofbargraph = driver.findElements(By.xpath("//*[name()='svg']/*[name()='g']//*[name()='rect']")).size();
	  for(int k=1;k<=sizeofbargraph;k++) {
		  if(k==6 || k==8 || k==17)
			{
				continue;
			}
	  driver.findElement(By.xpath("(//*[name()='svg']/*[name()='g']//*[name()='rect'])[" + k + "]")).click();
	  System.out.println(sizeofbargraph);
	  Thread.sleep(4000);
	  int sizeoflist = driver.findElements(By.xpath("//*[name()='svg']/*[name()='g']//*[name()='circle']")).size();
	   System.out.println(sizeoflist);
	   for(int i=1;i<=sizeoflist;i++)
	{
		
		WebElement element = driver.findElement(By.xpath("(//*[name()='svg']/*[name()='g']//*[name()='circle'])[" + i +"]")); 
		objLP.onMouseOver(element);
  		String text = objLP.getdata.getText();
  		System.out.println(text);
  		Thread.sleep(3000);
  		for(int j=0;j<arrayoflist.size();j++)
		{
		   Jsonobject jo = arrayoflist.get(j);
 			String value = jo.getXval()+" :\n"+jo.getYval();

 			if(value.equalsIgnoreCase(text))
 			{
 				
 				System.out.println("value present in web '"+ text +"' and value present in JSON Obj '"+ value +"'");
// 				break;
 			}
 		
		}
	}
		Thread.sleep(3000);
 		objLP.backbutton.click();
 		Thread.sleep(3000);
	   
		}
}
  
}
	


