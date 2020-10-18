package com.realestate.sanity.tests;

import static org.testng.Assert.assertEquals;

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
import com.realestate.pom.LoginPOM;
import com.realestate.pom.PropertiesPOM;
import com.realestate.utility.DriverFactory;
import com.realestate.utility.DriverNames;

public class RestorePropertyTestsRETC_53 {

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

}
