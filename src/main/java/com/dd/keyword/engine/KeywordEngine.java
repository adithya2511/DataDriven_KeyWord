package com.dd.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dd.keyword.base.BaseClassDD;

public class KeywordEngine {
	public BaseClassDD base;
	
	public WebDriver driver;
	public Properties prop;
	public WebElement element;
	
	public static  Workbook book;
	public static Sheet sheet;
	
	
	
	public  String scenario_Sheet_Path  = "C:\\Users\\devik\\eclipse-workspace\\DataDrivenAutomation\\src\\main\\java\\com\\dd\\keyword\\scenarios\\scenarios.xlsx";
	
	public void startExecution (String sheetName) {
		String locatorName = null;
		String locatorValue  = null;
		
		FileInputStream inputFile = null;
		try {
			inputFile = new FileInputStream(scenario_Sheet_Path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(inputFile);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet =book.getSheet(sheetName);
		int i = 0, k = 0; 
		for( i = 0;  i < sheet.getLastRowNum();  i++) {
			try {
			String locatorColumnValue = sheet.getRow(i+1).getCell(k+1).toString().trim();
			if(! locatorColumnValue.equalsIgnoreCase("NA")) {
				locatorName = locatorColumnValue.split("=")[0].trim();
				locatorValue = locatorColumnValue.split("=")[1].trim();
			}
			
			String actions = sheet.getRow(i+1).getCell(k+2).toString().trim();
			String value = sheet.getRow(i+1).getCell(k+3).toString().trim();
			
			switch(actions) {
			case "open browser" : 
				base = new BaseClassDD ();
				prop = base.initProperties();
				if(value.isEmpty() || value.equals("NA")) {
				driver = base.initDriver(prop.getProperty("browser"));
				}else {
					driver = base.initDriver(value);
				}
				break ;		
			case "enter url" :
				if(value.isEmpty() || value.equals("NA")) {
					driver.get(prop.getProperty("url"));
				}else {
					driver.get(value);
				}
				break ;
			default :
				break ;
			}
			switch(locatorName) {
			case "id" :
				element = driver.findElement(By.id(locatorValue));
				if(actions.equalsIgnoreCase("sendkeys")) {
				element.clear();
				element.sendKeys(value);
				}else if(actions.equalsIgnoreCase("click")){
					element.click();
				}
				locatorValue  = null;
				break ;
			case "name" : 
				element = driver.findElement(By.name(locatorValue));
				if(actions.equalsIgnoreCase("sendkeys")) {
				element.clear();
				element.sendKeys(value);
				}else if(actions.equalsIgnoreCase("click")){
					element.click();
				}
				locatorValue  = null;
				break ;
			case "xpath" :
				element = driver.findElement(By.xpath(locatorValue));
				if(actions.equalsIgnoreCase("sendkeys")) {
				element.sendKeys(value);
				}else if(actions.equalsIgnoreCase("click")){
					element.click();
				}
				locatorValue  = null;
				break ;
			default :
				break ;
			}
			}catch (Exception e) {
			}
		}
	}
}
