package com.realestate.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.realestate.generics.ScreenShot;
import com.realestate.pom.BlogPOM;
import com.realestate.pom.LoginPOM;
import com.realestate.pom.PropertiesPOM;
import com.realestate.utility.DriverFactory;
import com.realestate.utility.DriverNames;

public class TestCases {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private PropertiesPOM propertiesPOM;
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
		propertiesPOM=new PropertiesPOM(driver);
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
	@Test(priority=0)
	public void deleteProperty() throws InterruptedException {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("First0");
		propertiesPOM.clickPropertiesLink();
		propertiesPOM.clickAllPropertiesLink();
		propertiesPOM.mouseOverProperty();
		propertiesPOM.clickTrashLink();
		Thread.sleep(3000);
		String actual=propertiesPOM.getMessage();
		String expected="1 post moved to the Trash. Undo";
		assertEquals(actual, expected);
		Thread.sleep(3000);
		propertiesPOM.goToTrashLink();
		propertiesPOM.mouseOverTrashProperty();
		propertiesPOM.clickDeleteLink();
		Thread.sleep(3000);
		String actual1=propertiesPOM.getMessage();
		String expected1="1 post permanently deleted.";
		assertEquals(actual1, expected1);
		Thread.sleep(3000);
	}
	@Test(priority =1 )
	public void restoreProperty() throws InterruptedException, AWTException {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("First");
		propertiesPOM.clickPropertiesLink();
		propertiesPOM.clickAllPropertiesLink();
		propertiesPOM.goToTrashLink();
		propertiesPOM.mouseOverTrashProperty();
		propertiesPOM.clickRestoreLink();
		Thread.sleep(3000);
		String expected="1 post restored from the Trash.";
		String actual=propertiesPOM.getMessage();
		assertEquals(actual, expected);
		Thread.sleep(3000);
		propertiesPOM.openNewWindow(baseUrl);
		Thread.sleep(3000);
		propertiesPOM.clickPropertiesLink();
		propertiesPOM.clickAllPropertiesLink();
		propertiesPOM.sendSearchInput();
		propertiesPOM.clickSearchProperties();
		screenShot.captureScreenShot("First1");
	}
	@Test(priority = 3)
	public void viewAndReplyToComment() throws AWTException, InterruptedException
	{
		blogPOM.clickBlog();
		blogPOM.clickReadMore();
		Thread.sleep(3000);
		blogPOM.enterComment("good");
		blogPOM.enterName("abhisdet3");
		blogPOM.enterEmail("abhifsitsdet3@somemailtest.com");
		blogPOM.submitComment();
		blogPOM.openNewWindow(baseUrl);
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		blogPOM.clickDashboard();
		Thread.sleep(3000);
		blogPOM.mouseOverComment();
		blogPOM.clickReplyLink();
		blogPOM.replyToComment("okay FSIT6");
		Thread.sleep(3000);
		blogPOM.clickReplyButton();
		blogPOM.refreshPage();
		Thread.sleep(3000);
		screenShot.captureScreenShot("Final");
		
		
	}
}
