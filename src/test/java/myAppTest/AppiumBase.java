package myAppTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumBase {
	AppiumDriver driver;
	AppiumDriverLocalService service;
	
	@Test
	public void configureAppium() throws MalformedURLException, URISyntaxException {

/*		service = new AppiumServiceBuilder()
		.withAppiumJS(new File("C:\\Users\\uday1\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
		.withIPAddress("127.0.0.1").usingPort(4723)
		.withTimeout(Duration.ofSeconds(100)).build();
		
		service.start();
*/		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "15");
		capabilities.setCapability("deviceName", "emulator-5554");
		capabilities.setCapability("automationName", "uiautomator2");			
		capabilities.setCapability("appPackage", "cm.aptoide.pt");
		capabilities.setCapability("appActivity", "cm.aptoide.pt.view.MainActivity");		
//		URI url = new URI("http://localhost:4723");
        URI url = new URI("http://127.0.0.1:4723");
		driver = new AppiumDriver(url.toURL(), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.quit();
//		service.stop();
	}

}
