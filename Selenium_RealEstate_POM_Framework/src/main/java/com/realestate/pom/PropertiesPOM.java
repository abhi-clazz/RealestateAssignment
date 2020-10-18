package com.realestate.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PropertiesPOM {
	private WebDriver driver; 
	
	public PropertiesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[contains(text(),'Properties')]")
	private WebElement propertiesLink;
	
	@FindBy(xpath="//*[@id='menu-posts-property']/ul/li[2]/a")
	private WebElement allPropertiesLink;
	
	@FindBy(xpath="//tr[2]//a[@class='submitdelete'][contains(text(),'Trash')]")
	private WebElement trashLink;
	
	@FindBy(xpath="//*[@class = 'trash']")
	private WebElement trashLnk;
	
	@FindBy(xpath="//table/tbody[@id='the-list']/tr[2]/td[1]")
	private WebElement mouseOverProperty;
	
	@FindBy(xpath="//table/tbody[@id='the-list']/tr[1]/td[1]")
	private WebElement mouseOverTrashProperty;
	
	@FindBy(xpath="//tr[1]//a[@class='submitdelete'][contains(text(),'Delete Permanently')]")
	private WebElement deleteLink;
	
	@FindBy(xpath="//tr[1]//a[contains(text(),'Restore')]")
	private WebElement restoreLink;
	
	@FindBy(xpath = "//*[@id='wpbody-content']/div[3]/ul/li[1]")
	private WebElement allLink;
	
	@FindBy(xpath="//*[@id='message']/p")
	private WebElement message;
	
	@FindBy(id="post-search-input")
	private WebElement searchInput;
	@FindBy(id="search-submit")
	private WebElement searchProperties;
	
	public void clickPropertiesLink() {
		this.propertiesLink.click(); 
	}
	
	public void clickAllPropertiesLink() {
		this.allPropertiesLink.click(); 
	}
	
	
	public void mouseOverProperty()
	{
		Actions act=new Actions(driver);
		act.moveToElement(mouseOverProperty).build().perform();
	}
	
	public void clickTrashLink() {
		
		this.trashLink.click(); 
	}
	
	public void goToTrashLink() {
		this.trashLnk.click(); 
	}
	public void mouseOverTrashProperty()
	{
		Actions act=new Actions(driver);
		act.moveToElement(mouseOverTrashProperty).build().perform();
	}
	public void clickDeleteLink() {
		this.deleteLink.click();
	}
	public void clickRestoreLink() {
		this.restoreLink.click();
	}
	
	public void clickAllLink()
	{
	this.allLink.click();
	}

	
	public String getPropertyText() {
		String txt=this.mouseOverTrashProperty.getText();
		return txt;
	}
	
	public void openNewWindow(String url) throws AWTException
	{
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		String parent = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String child : windows)
		{
			if (!child.equals(parent))
			{
				driver.switchTo().window(child);
				driver.get(url);
			}
		/*((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String>tablist=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tablist.get(1));
		driver.get("http://realty-real-estatem1.upskills.in/admin");*/
		}
	}
	
	
	
	
	public void sendSearchInput() {
		String txt=getPropertyText();
		this.searchInput.clear();
		this.searchInput.sendKeys(txt);
	}
	public void clickSearchProperties()
	{
		this.searchProperties.click();
	}

	public String getMessage() {
		return this.message.getText();
	}
}
