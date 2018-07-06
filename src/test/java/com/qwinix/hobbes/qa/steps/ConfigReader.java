package com.qwinix.hobbes.qa.steps;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	Properties pro;
	public ConfigReader() {
		try {
			File src = new File("./Configuration/config.property");
			FileInputStream fis = new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		
		} 
		catch (Exception e){
		System.out.println("Exception is " + e.getMessage());
		}
	}
	
	public String getBrowser() {
		String path = pro.getProperty("Browser");
		return path;
	}
	
	public String getChromePath() {
		String chromePath = pro.getProperty("ChromeDriver");
		return chromePath;
	}
	
	public String getFirefoxPath() {
		String firefoxpath = pro.getProperty("FirefoxDriver");
		return firefoxpath;
	}
	
	public String getUrl(){
		String url = pro.getProperty("Url");
		return url;
	}

}
