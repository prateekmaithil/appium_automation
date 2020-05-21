package gojek.testcases;

		

	import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

import com.gojek.qa.base.TestBase;
import com.gojek.qa.pages.HomePages;
import com.gojwk.qa.util.TestUtil;

	public class HomepageTest extends TestBase {
		HomePages homePage;
		TestUtil testUtil;
		
		public HomepageTest() {
			super();
			homePage = new HomePages();
		}

		//test cases should be separated -- independent with each other
		//before each test case -- launch the browser and login
		//@test -- execute test case
		//after each test case -- close the browser
		
		@BeforeMethod
		public void setUp() {
			initialization();
			testUtil = new TestUtil();
		}
		
		
		@Test(priority=1)
		public void successPaymentFlowTest(){
			homePage.BuyNowLink();
			homePage.checkOutLink();
			testUtil.switchToFrame();
			homePage.ContinueLink();
			homePage.CreditLink();
			boolean result = homePage.populateCreditCardSuccessDetails();
			Assert.assertEquals(result, true);
		}
		
		
		
		@Test(priority=2)
		public void failedPaymentFlowTest(){
			homePage.BuyNowLink();
			homePage.checkOutLink();
			testUtil.switchToFrame();
			homePage.ContinueLink();
			homePage.CreditLink();
			boolean result = homePage.populateCreditCardFailDetails();
			Assert.assertEquals(result, true);
		}
		
		@AfterMethod
		public void tearDown(){
			driver.quit();
		}
	}

