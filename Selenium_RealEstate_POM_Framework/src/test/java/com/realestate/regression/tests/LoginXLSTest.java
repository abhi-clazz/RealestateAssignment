package com.realestate.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.realestate.bean.LoginBean;
import com.realestate.dao.ELearningDAO;
import com.realestate.dataproviders.LoginDataProviders;
import com.realestate.generics.ScreenShot;
import com.realestate.pom.LoginPOM;
import com.realestate.pom.PropertiesPOM;
import com.realestate.readexcel.ReadExcel;
import com.realestate.utility.DriverFactory;
import com.realestate.utility.DriverNames;

public class LoginXLSTest {
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

	@Test(dataProvider = "xls-inputs", dataProviderClass = LoginDataProviders.class)
	public void loginTest(String userName, String password) throws InterruptedException {
		loginPOM.sendUserName(userName);
		loginPOM.sendPassword(password);
		loginPOM.clickLoginBtn();
		screenShot.captureScreenShot(userName);
		
	}

}