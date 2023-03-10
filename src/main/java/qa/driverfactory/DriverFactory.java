package qa.driverfactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	
	WebDriver driver;
    static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    
    public WebDriver init_driver(String browser) {
    	
    	System.out.println("you are using following browser"+browser);
    	
    	if(browser.equals("chrome")) {
    		
    		WebDriverManager.chromedriver().setup();
    		tlDriver.set(new ChromeDriver());
    	}else if(browser.equals("firefox")) {
    		WebDriverManager.firefoxdriver().setup();
    		tlDriver.set(new FirefoxDriver());
    	}else {
    		System.out.println("Please check browser and enter it properly");
    	}
    	getDriver().manage().window().maximize();
    	getDriver().manage().deleteAllCookies();
    	getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	
    	return getDriver();
    }
    
    public static synchronized WebDriver getDriver() {
    	return tlDriver.get();
    }
}
