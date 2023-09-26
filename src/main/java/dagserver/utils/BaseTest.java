package dagserver.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected WebDriver driver;

	@BeforeTest
	protected void beforeSuite(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	@AfterTest
    protected void close() {
        driver.quit();
    }

}
