package com.gojek.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.gojek.qa.base.TestBase;
import com.gojwk.qa.util.TestUtil;
	
public class HomePages extends TestBase {
		//Page Factory - OR:
		@FindBy(xpath="//a[@class='btn buy']")
		WebElement buyNowLink;
		
		@FindBy(xpath="//div[@class='cart-checkout']")
		WebElement checkOut;
		
		@FindBy(xpath="//a[@class='button-main-content']")
		WebElement Continue;
		
		
		@FindBy(xpath = "//a[contains(@href,'credit-card')]")
		WebElement creditCard;
		
	//	WebElement buyNowLink =	driver.findElement(By.xpath("//a[@class='btn buy']"));
		
		//WebElement cardNumber = driver.findElement(By.xpath("//input[@name='cardnumber']"));
		//WebElement expirtDate = driver.findElement(By.xpath("//input[@placeholder='MM / YY']"));
		//WebElement cvv = driver.findElement(By.xpath("//input[@style='font-family: cvvpass;']"));

		
		
		// Initializing the Page Objects:
		public HomePages() {
			//PageFactory.initElements(driver, this);
		}

	
		//Actions:
		public void BuyNowLink(){
			//System.out.println(buyNowLink.);
			TestUtil.waitUntilElementIsClickable(By.xpath("//a[@class='btn buy']"));
			//buyNowLink.click();
        	driver.findElement(By.xpath("//a[@class='btn buy']")).click();
		}
	
		public void checkOutLink(){
			//System.out.println(checkOut.getText());
			TestUtil.waitUntilElementIsClickable(By.xpath("//div[@class='cart-checkout']"));
			//checkOut.click();
			driver.findElement(By.xpath("//div[@class='cart-checkout']")).click();
		}

		public void ContinueLink(){
			TestUtil.waitUntilElementIsClickable(By.xpath("//a[@class='button-main-content']"));
			//checkOut.click();
			driver.findElement(By.xpath("//a[@class='button-main-content']")).click();
		}
		
		public void CreditLink(){
			TestUtil.waitUntilElementIsClickable(By.xpath("//a[contains(@href,'credit-card')]"));
			//checkOut.click();
			driver.findElement(By.xpath("//a[contains(@href,'credit-card')]")).click();
		}
			
		public boolean populateCreditCardFailDetails() {
			try 
			{
				Object[][] data = TestUtil.getTestData("data");
		
				driver.findElement(By.xpath("//input[@name='cardnumber']")).sendKeys(data[1][1].toString());
				driver.findElement(By.xpath("//input[@placeholder='MM / YY']")).sendKeys(data[1][2].toString().replace("//","\\"));
				driver.findElement(By.xpath("//input[@style='font-family: cvvpass;']")).sendKeys(data[1][3].toString().replaceAll(".0", ""));
				ContinueLink();
				WebElement frame = driver.findElement(By.xpath("//*[@id=\"application\"]//iframe"));
				driver.switchTo().frame(frame);
				TestUtil.waitUntilElementIsClickable(By.xpath("//input[@name='PaRes']"));
				driver.findElement(By.xpath("//input[@name='PaRes']")).sendKeys(data[1][4].toString().replaceAll(".0", ""));
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				driver.switchTo().parentFrame();// .frame("snap-midtrans");
				TestUtil.waitUntilElementIsClickable(By.xpath("//span[contains(text(),'Transaction failed')]"));
				return true;
			}
			catch(Exception e) {
				return false;
			}
		}
		
		public boolean populateCreditCardSuccessDetails() {
			try 
			{
				Object[][] data = TestUtil.getTestData("data");
		
				driver.findElement(By.xpath("//input[@name='cardnumber']")).sendKeys(data[0][1].toString());
				driver.findElement(By.xpath("//input[@placeholder='MM / YY']")).sendKeys(data[0][2].toString().replace("//","\\"));
				driver.findElement(By.xpath("//input[@style='font-family: cvvpass;']")).sendKeys(data[0][3].toString().replaceAll(".0", ""));
				ContinueLink();
				WebElement frame = driver.findElement(By.xpath("//*[@id=\"application\"]//iframe"));
				driver.switchTo().frame(frame);
				TestUtil.waitUntilElementIsClickable(By.xpath("//input[@name='PaRes']"));
				driver.findElement(By.xpath("//input[@name='PaRes']")).sendKeys(data[0][4].toString().replaceAll(".0", ""));
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				driver.switchTo().parentFrame(); //frame("snap-midtrans");
				TestUtil.waitUntilElementIsClickable(By.xpath("//div[contains(text(),'Transaction successful')]"));
				return true;
			}
			catch(Exception e) {
				return false;
			}
		}
		
}
