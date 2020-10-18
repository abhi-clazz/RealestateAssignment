package com.realestate.sanity.tests;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.realestate.generics.ScreenShot;
import com.realestate.pom.BlogPOM;
import com.realestate.pom.LoginPOM;
import com.realestate.utility.DriverFactory;
import com.realestate.utility.DriverNames;

public class ViewAndReplyCommentTestsRETC_54 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private BlogPOM blogPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("src/main/resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		blogPOM=new BlogPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void viewAndReplyToComment() throws AWTException, InterruptedException
	{
		blogPOM.clickBlog();
		blogPOM.clickReadMore();
		Thread.sleep(3000);
		blogPOM.enterComment("good");
		blogPOM.enterName("abhilashfsit54");
		blogPOM.enterEmail("abhfsit34@somemailtest.com");
		blogPOM.submitComment();
		blogPOM.openNewWindow(baseUrl);
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		blogPOM.clickDashboard();
		Thread.sleep(3000);
		blogPOM.mouseOverComment();
		blogPOM.clickReplyLink();
		blogPOM.replyToComment("okay FSIT5");
		Thread.sleep(3000);
		blogPOM.clickReplyButton();
		blogPOM.refreshPage();
		Thread.sleep(3000);
		screenShot.captureScreenShot("Final");
		
		
	}
}
