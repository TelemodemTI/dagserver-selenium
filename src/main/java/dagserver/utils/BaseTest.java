package dagserver.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected WebDriver driver;
	protected ScreenShootUtils screen;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
	protected Properties application;
	
	@BeforeMethod
	@Parameters({"chromeDriverUrl"})
	protected void beforeTest(String chromeDriverUrl) throws EncryptedDocumentException, IOException{
		
		this.application = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
        this.application.load(inputStream);
		if(this.application.getProperty("driver.mode").equals("DOCKER")) {
			URL url = new URL(chromeDriverUrl);
			ChromeOptions options = new ChromeOptions(); 
			driver = new RemoteWebDriver(url,options);	
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();	
		}
		driver.manage().window().maximize();
		screen = new ScreenShootUtils();
	}
	@AfterMethod
    protected void close() throws IOException {
		driver.quit();
	}
	
	
  protected void writeEvidence(ITestContext context, String testName, String status, By xpath) throws IOException {
	  var screenshot = this.getScreenshoot(testName+"-"+status,xpath);
	  Map<String,byte[]> map = new HashMap<>();
	  map.put(testName+"-"+status,screenshot);
	  this.reportScreen(map);
  }

  protected void reportScreen(Map<String,byte[]> fileContent) {
     Reporter.log("Fotos tomadas durante la prueba<br>");
     var screens = fileContent.keySet();
     List<String> list = new ArrayList<>(screens);
     Collections.sort(list, Collections.reverseOrder());
     for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
	   String string = iterator.next();
	   var content = fileContent.get(string);
	   String encodedString = Base64.getEncoder().encodeToString(content);
	   String title = "<b>"+string+"</b><br>";
	   String path = title + "<img src=\"data:image/png;base64, " + encodedString + "\" width=\"700\" height=\"480\" /><br>";
	   Reporter.log(path);
     }   
    }
	
	protected byte[] getScreenshoot(String tag,By xpath) throws IOException {
		var bytes = this.screen.takeScreenShotAllPage(driver,xpath);
		return bytes;
		
	}
	
	
	protected String getInfrastructure(String className, String key) throws IOException {
		Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("infra.properties");
        properties.load(inputStream);
        return properties.getProperty(className+"."+key);
	}
}
