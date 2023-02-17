package com.dd.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClassDD {
	Properties prop;
	WebDriver driver;
	
	public WebDriver initDriver (String browserName){
			System.setProperty("webdriver.chrome.driver","C:\\Users\\devik\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options); 
				return driver;
	}
	
	public Properties initProperties () {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\devik\\eclipse-workspace\\PomTestAutomation\\src\\main\\java\\"
					+ "com\\fb\\configurations\\configurations.properties");	
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
