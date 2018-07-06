package com.qwinix.hobbes.qa.steps;

import java.sql.Driver;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qwinix.hobbes.qa.BaseDriver;
import com.qwinix.hobbes.qa.page.LoginPage;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RadialchartStep {
	ConfigReader config = new ConfigReader();
	WebDriver driver = BaseDriver.driver;
	ArrayList<Jsonobject> arrayoflist = new ArrayList<>();
		LoginPage objLP= new LoginPage ();
	
			public void openurl() {
					driver.get(config.getUrl());
					driver.manage().window().maximize();
		
			}

public void login(){
   objLP.loginbutton.click();
   objLP.email.sendKeys("qwinix@yopmail.com");
   objLP.password.sendKeys("Qwinix123!");
   objLP.submit.click();


}



	public void RadialAccessToken() throws InterruptedException
  	{
  		Thread.sleep(5000);
  		JavascriptExecutor js = (JavascriptExecutor)driver;
  		String token_id = js.executeScript("return localStorage.getItem('access_token')").toString();
		String email = ("qwinix@yopmail.com").toString();
		Response  userAndPullreq = RestAssured.given().baseUri("http://gitviz.qwinix.net:8000").basePath("usersAndPullreq?usersCount=20").header("Authorization","Bearer "+token_id).contentType("application/json").log().body().when().get();
		String Stringjson = userAndPullreq.asString();
		System.out.println(Stringjson);
				
		
		
		JSONObject objects = new JSONObject(Stringjson);
		
		try
		{
			JSONArray pullrequest = objects.getJSONArray("usersAndPullreq");
			for(int i=0;i<pullrequest.length();i++)
			{
				
				Jsonobject jobject = new Jsonobject();
				JSONObject value = pullrequest.getJSONObject(i);
				jobject.setXval(value.getString("label"));
				jobject.setYval(value.getInt("theta"));
				arrayoflist.add(jobject);
			}
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
  	}


public void fetchdata() throws InterruptedException {
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("window.scrollBy(0,450)", "");
	  int sizeofbargraph = driver.findElements(By.xpath("//div[@class='col-md-6 col-xs-6']//*[name()='svg']/*[name()='g']/*[name()='path']")).size();
	  System.out.println(sizeofbargraph);
	  for(int i=1;i<=sizeofbargraph;i++)
	  {
		  
		  Thread.sleep(5000);
		  driver.findElement(By.xpath("(//div[@class='col-md-6 col-xs-6']//*[name()='svg']/*[name()='g']/*[name()='path'])[" + i + "]")).click();
		  Thread.sleep(4000);
		  int sizeoflist = driver.findElements(By.xpath("//*[name()='svg']/*[name()='g']/*[name()='path']")).size();
		  System.out.println(sizeoflist);
		  for(int j=1;j<=sizeoflist;j++)
		  {
			  WebElement element = driver.findElement(By.xpath("//*[name()='svg']/*[name()='g']/*[name()='path'][" + j +"]")); 
			  Thread.sleep(4000);
			  objLP.onMouseOver(element);
			  Thread.sleep(4000);
			  String text = objLP.getdata.getText();
			  System.out.println(text);
			  Thread.sleep(3000);
			  for(int k=0;k<arrayoflist.size();k++)
			  {
				  Jsonobject jo = arrayoflist.get(k);
				  String value = jo.getXval()+" :\n"+jo.getYval();

				  if(value.equalsIgnoreCase(text))
				  {
 				
					  System.out.println("value present in web '"+ text +"' and value present in JSON Obj '"+ value +"'");
//					  break;
				  }
 		
			  }
		  }
		  Thread.sleep(3000);
		  objLP.backbutton.click();
		  Thread.sleep(3000);
	  }
	}
}
  



