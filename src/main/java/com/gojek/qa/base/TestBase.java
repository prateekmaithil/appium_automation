package com.gojek.qa.base;




import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.gojwk.qa.util.TestUtil;
import com.gojwk.qa.util.WebEventListener;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;

import java.net.ServerSocket;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;


 


public class TestBase {
	
	
//ublic static WebDriver driver;
	public static Properties prop;
//ublic  static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;
	public static AppiumDriverLocalService service;
	
	
	
	
public static AppiumDriver<MobileElement> capabilities;
	
//ublic static AppiumDriver<MobileElement> m_driver;
	
//ublic static AppiumDriver<WebElement>idriver;

	
	
	
//ublic static IOSDriver driver;
	
    
	
	public AppiumDriverLocalService startServer()
	{
		//
	boolean flag=	checkIfServerIsRunnning(4723);
	if(!flag)
	{
		
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
	}
		return service;
		
	}
	
public static boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
public static void startEmulator() throws IOException, InterruptedException
{

	Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
	Thread.sleep(6000);
}
	


	public AppiumDriver<MobileElement> getDriver() {
		
		
		try {
//	PageFactory.initElements(m_driver,this);
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/gojek"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
						
		String platform =prop.getProperty("platform");
	if (platform.equals("Android")) {
	    // setup the android driver
	    DesiredCapabilities cap = new DesiredCapabilities();
	    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "**TBD**");
	    
	    cap.setCapability(MobileCapabilityType.DEVICE_NAME, "**TBD**");
// cap.setCapability(MobileCapabilityType.APP, appUrl);
	    cap.setCapability(AndroidMobileCapabilityType.NO_SIGN, true);
//   cap.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true); //set unicodekeyboard in order to support umlauts
//   cap.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true); //reset keyboard after tests
	    cap.setCapability(AndroidMobileCapabilityType.IGNORE_UNIMPORTANT_VIEWS, true); //speeds up tests, as views that are not important i.e. for accessiblity are not shown on android. Is disabled on demand in tests using driver.ignoreunimporantViews
   try {
	   capabilities  = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), cap);
} catch (MalformedURLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
       

	
	} else if (platform.equals("iOS")) {
	    // setup the ios driver
	    DesiredCapabilities cap = new DesiredCapabilities();
	    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.3");
	    cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
// cap.setCapability(MobileCapabilityType.APP, appUrl);
	     
 try {
	 capabilities  = new IOSDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), cap);
} catch (MalformedURLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  
	}
	return capabilities;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public static IOSDriver driver;
	
    
    	
    	public AppiumDriverLocalService startServer()
    	{
    		//
    	boolean flag=	checkIfServerIsRunnning(4723);
    	if(!flag)
    	{
    		
    		service=AppiumDriverLocalService.buildDefaultService();
    		service.start();
    	}
    		return service;
    		
    	}
    	
    public static boolean checkIfServerIsRunnning(int port) {
    		
    		boolean isServerRunning = false;
    		ServerSocket serverSocket;
    		try {
    			serverSocket = new ServerSocket(port);
    			
    			serverSocket.close();
    		} catch (IOException e) {
    			//If control comes here, then it means that the port is in use
    			isServerRunning = true;
    		} finally {
    			serverSocket = null;
    		}
    		return isServerRunning;
    	}
    public static void startEmulator() throws IOException, InterruptedException
    {

    	Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
    	Thread.sleep(6000);
    }

    @Test
    	public static  AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException
    	{
    		
    FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\practise\\AppiumFramework\\global.properties");
    		Properties prop=new Properties();
    		prop.load(fis);
    		
    	

    		// TODO Auto-generated method stub
    	 File appDir = new File("src");
         File app = new File(appDir, (String) prop.get(appName));
         DesiredCapabilities capabilities = new DesiredCapabilities();
     // String device=(String) prop.get("device");
        String device= System.getProperty("deviceName");
      if(device.contains("emulator"))
      {
      startEmulator();
      }
         capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        
         capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
         capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14);
         
  //       capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
         AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	    
    	    return driver;
    	}
    
    	
    	  @Test
    	    public void testAppiumProSite_iOS() throws MalformedURLException {
    	        DesiredCapabilities capabilities = new DesiredCapabilities();
    	        capabilities.setCapability("platformName", "iOS");
    	        capabilities.setCapability("platformVersion", "11.2");
    	        capabilities.setCapability("deviceName", "iPhone 7");
    	        capabilities.setCapability("browserName", "Safari");

    	        // Open up Safari
    	        IOSDriver driver = new IOSDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
    	        actualTest(driver);
    	    }
    
    
    
	public TestBase(){
		try {
			PageFactory.initElements(driver, this);
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/gojek"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void initialization(){
		

		/*		
		 String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")){             
			System.setProperty("webdriver.chrome.driver", "/Users/DELL 3/eclipse-workspace/Gojek/webdriver/chromedriver.exe");	
			
			
			
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "/Users/DELL 3/eclipse-workspace/Gojek/webdriver/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("platformVersion", "11.2");
		capabilities.setCapability("deviceName", "iPhone 7");
		capabilities.setCapability("browserName", "Safari");
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		*/
		

		
	
		
		
	
	
	
	

}
	




