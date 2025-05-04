package myAppTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;

public class AppInfoset {

	AppiumDriver driver;
	
	@Test
	public void capability() throws MalformedURLException, URISyntaxException {
					
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
		}
	}

