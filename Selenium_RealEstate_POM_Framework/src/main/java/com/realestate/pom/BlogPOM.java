package com.realestate.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BlogPOM {
	private WebDriver driver;

	public BlogPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "BLOG")
	private WebElement blog;


	@FindBy(xpath = "//h3//a[text()='New Launch']")
	private WebElement readMore;

	@FindBy(xpath="//*[@class = 'read-more']")
	private WebElement readMore1;

	@FindBy(xpath = "//textarea[@name='comment']")
	private WebElement Comment;

	@FindBy(xpath = "//input[@id='author']")
	private WebElement name;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement email;

	@FindBy(xpath = "//input[@id='submit']")
	private WebElement commentSubmitbutton;

	@FindBy(xpath = "//div[contains(text(),'Dashboard')]")
	private WebElement dashboard;

	@FindBy(xpath = "//ul[1]/li[1]/div[1]/p[1]")
	private WebElement comment;


	@FindBy(xpath="//*[text()='Reply']")
	private WebElement replyLink;

	@FindBy(xpath = "//textarea[@id='replycontent']")
	private WebElement replyComment;

	@FindBy(xpath = "//span[@id='replybtn']")
	private WebElement sendReply;

	@FindBy(xpath = "//div[contains(text(),'Dashboard')]")
	private WebElement refresh;


	public void clickBlog() {
		this.blog.click();
	}



	public void clickReadMore() throws AWTException {

		Actions act=new Actions(driver);
		act.moveToElement(readMore).build().perform();
		readMore1.click();

	}




	public void enterComment(String comment) throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		this.Comment.sendKeys(comment);

	}

	public void enterName(String name) {

		this.name.sendKeys(name);

	}

	public void enterEmail(String email)  {

		this.email.sendKeys(email);

	}



	public void submitComment()  {
		commentSubmitbutton.click();
	}
	public void openNewWindow(String url) throws AWTException {
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
		}

	}



	public void clickDashboard() {
		this.dashboard.click();
	}



	public void mouseOverComment() {
		Actions action = new Actions(driver);
		action.moveToElement(this.comment).build().perform();
	}



	public void clickReplyLink() {
		this.replyLink.click();
	}



	public void replyToComment(String replyComment) {
		this.replyComment.sendKeys(replyComment);
	}



	public void clickReplyButton() {
		this.sendReply.click();
	}




	public void refreshPage() {
		this.refresh.click();
	}

}