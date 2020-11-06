package com.crossover.e2e;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import qa.automation.java.TestCase;

public class GMailTest extends TestCase {

	public GMailTest() {
		super();
	}

	@BeforeTest
	public void setup() throws IOException {
		browserLaunch();
		
	}

	@Test(priority = 1)
	public void sendingEmail() throws InterruptedException {	
		
		
		WebElement userElement = driver.findElement(By.xpath("//input[@id='identifierId']"));
		userElement.sendKeys(properties.getProperty("username"));
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		Thread.sleep(3000);

		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.sendKeys(properties.getProperty("password"));
		driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//textarea[@name='to']"))
				.sendKeys(String.format("%s@gmail.com", properties.getProperty("username")));

		WebElement emailSubject = driver.findElement(By.xpath("//input[@placeholder='Subject']"));
		emailSubject.sendKeys(properties.getProperty("email.subject"));
		Thread.sleep(5000);

		WebElement emailBody = driver.findElement(By.xpath("//div[@aria-label='Message Body']"));
		emailBody.sendKeys(properties.getProperty("email.body"));
		driver.findElement(By.xpath("(//div[@class='J-J5-Ji J-JN-M-I-JG'])[5]")).click();

		Actions action = new Actions(driver);
		WebElement act = driver.findElement(By.xpath("//span[@class='J-Ph-hFsbo']"));
		action.moveToElement(act).build().perform();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='J-LC-Jz'][contains(text(),'Social')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@aria-label='Social']")).click();
		
		driver.findElement(By.xpath("//span[@name='me']/parent::span/parent::div/parent::td/parent::tr/td[3]")).click();
		
		Thread.sleep(3000);  
		driver.findElement(By.xpath("(//span[@name='me']/parent::span/parent::div)[2]")).click();

		// Verify Social Label
		String ActualTitle = driver.findElement(By.xpath("//div[@title='Search for all messages with label Social']"))
				.getText();
		String ExpectedTitle = "Social";

		Assert.assertEquals(ActualTitle, ExpectedTitle);
		System.out.println("Label Verifed");

		// Verify Email Subject
		String ActualTitle1 = driver.findElement(By.xpath("//div[@class='ha']/h2[@class='hP']")).getText();
		String ExpectedTitle1 = "Testing Social Label";

		Assert.assertEquals(ActualTitle1, ExpectedTitle1);
		System.out.println("Subject Verified");

		// Verified Email Body
//		String ActualTitle2 = driver.findElement(By.xpath("(//div[@dir='ltr'])[4]")).getText();
		String ActualTitle2 = driver.findElement(By.xpath("//div[contains(text(),'Hi, Testing to Automate Composing.')]")).getText();
		System.out.println(ActualTitle2);
		String ExpectedTitle2 = "Hi, Testing to Automate Composing.";

		Assert.assertEquals(ActualTitle2, ExpectedTitle2);
		System.out.println("Email Body Verified");

	}

	@AfterTest
	public void tearDown() {
		browserQuit();
	}

}
