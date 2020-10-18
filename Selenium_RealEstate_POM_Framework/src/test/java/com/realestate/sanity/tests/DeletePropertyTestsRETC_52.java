package com.realestate.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.realestate.generics.ScreenShot;
import com.realestate.pom.LoginPOM;
import com.realestate.pom.PropertiesPOM;
import com.realestate.utility.DriverFactory;
import com.realestate.utility.DriverNames;

public class DeletePropertyTestsRETC_52 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private PropertiesPOM propertiesPOM;
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

	
}
