package myAppTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppStatus {
//	AppiumDriver driver;
	AndroidDriver driver;
	AppiumDriverLocalService service;

/*	@BeforeTest
	public void StartAppiumServer() {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\uday1\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723)
				.withTimeout(Duration.ofSeconds(100)).build();
				service.start();				
	}           */
	
	@Test
	public void LaunchApp() throws MalformedURLException, URISyntaxException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "15");
		capabilities.setCapability("deviceName", "emulator-5554");
		capabilities.setCapability("automationName", "uiautomator2");			
//		capabilities.setCapability("appPackage", "cm.aptoide.pt");
//		capabilities.setCapability("appActivity", "cm.aptoide.pt.view.MainActivity");		
//		URI url = new URI("http://localhost:4723");
        URI url = new URI("http://127.0.0.1:4723");
//		driver = new AppiumDriver(url.toURL(), capabilities);
        driver = new AndroidDriver(url.toURL(), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		boolean IsAppInstalled = driver.isAppInstalled("io.appium.android.apis");
		
		if (IsAppInstalled) {
			
			driver.startActivity(new Activity("io.appium.android.apis", "io.appium.android.apis.ApiDemos"));
			Thread.sleep(5000);
		} else {
			driver.installApp("C:\\Users\\uday1\\Documents\\ApiDemos-debug.apk");
			Thread.sleep(3000);
		try {
			driver.startActivity(new Activity("io.appium.android.apis", "io.appium.android.apis.ApiDemos"));
			Thread.sleep(8000);
		} catch (Exception e) {
		}
//			driver.findElement(By.id("com.android.permissioncontroller:id/continue_button")).click();
//			Thread.sleep(5000);
//			driver.findElement(By.id("android:id/button1")).click();
//			Thread.sleep(2000);
		WebElement Text = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Text\"]"));
		System.out.println(Text.getText());
		Text.click();
		}
		WebElement Texter = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Text\"]"));
		System.out.println(Texter.getText());
		Texter.click();
		//AppState
		ApplicationState Appstate1 = driver.queryAppState("io.appium.android.apis");
		System.out.println("Current App State-"+Appstate1);
		// Run App in background
		driver.runAppInBackground(Duration.ofSeconds(5));
		//terminate app
		driver.terminateApp("io.appium.android.apis");
		//queryAppState
		ApplicationState Appstate2 = driver.queryAppState("io.appium.android.apis");
		System.out.println("Current App State-"+Appstate2);
		//activate youtube app
//		driver.activateApp("com.google.android.youtube");
		//remove App
		driver.removeApp("io.appium.android.apis");
		}
	}
/*	@AfterTest
	public void TearDown() {
		if (service.isRunning() == true) {
			service.stop();
			System.out.println("Appium Server Stop...");
			
}}
*/
