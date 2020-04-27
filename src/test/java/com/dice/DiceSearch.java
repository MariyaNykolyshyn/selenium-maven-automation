package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceSearch {
	public static void main(String[]args) throws Exception {
		//setup chromedriver path
		WebDriverManager.chromedriver().setup();
		// help to open chrome
		WebDriver driver = new ChromeDriver();
		//get to needed page
		String url = "https://www.dice.com/";
		driver.get(url);
		//verifing if page has already opened
		String actualTitle = driver.getTitle();
		String expectedTitle = "Find Jobs in Tech | Dice.com | Find Jobs in Tech";
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Step Pass. Dice page loaded.");
		}else {
			System.out.println("step failed");
			throw new Exception("Fail");
		}		
		//fullscreen
		driver.manage().window().maximize();
		//waits, if browser is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//find by xpath
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/dhi-job-search-bar/div/div/js-search-input/div/form/div/div[1]/div/dhi-new-typeahead-input/div/input")).sendKeys("QA tester");
		//find locator by id
		driver.findElement(By.id("google-location-search")).sendKeys("60090");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("submitSearch-button")).click();
		
		String count = driver.findElement(By.id("totalJobCount")).getText();
		System.out.println(count);
		int countResult = Integer.parseInt(count.replace(",", ""));// if amount is bigger than 1 0000, because it will have "," in it(example 2,567)
		
		// verify that all positions showed up
		if(countResult> 0) {
			System.out.println("Step 2. Passed");
		}else {
			System.out.println("Step 2. Failed");
		}
		
		driver.close();
	}
}
