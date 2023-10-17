package dagserver.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected WebDriver driver;
	protected ScreenShootUtils screen;
	protected String screenpath;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
	@BeforeTest
	protected void beforeTest() throws EncryptedDocumentException, IOException{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		screen = new ScreenShootUtils();
		this.screenpath = "C:\\tmp\\test\\";
	}
	@AfterTest
    protected void close() throws IOException {
		driver.quit();
	}
	
	public String saveScreenshoot(String tag,By xpath) throws IOException {
		var bytes = this.screen.takeScreenShotAllPage(driver,xpath);
		String identifier = ScreenShootUtils.generateIdentifier(10);
		String filename = screenpath + identifier + "_" + sdf.format(new Date()) + "_" + tag + ".png";
		FileUtils.writeByteArrayToFile(new File(filename), bytes);
		return filename;
	}
	
	public void writeEvidence(ITestContext context,String name, String status, By target) throws IOException {
		var classname = this.getClass().toString();
		String filename = this.saveScreenshoot(name, target);
		Map<String,String> data = new HashMap<String,String>();
		data.put("name", name);
		data.put("classname", classname);
		data.put("status", status);
		data.put("screenshoot", filename);
		
		List<Map<String, String>> list = null;
		if(context.getSuite().getAttribute("dataOutput") != null) {
			list = (List<Map<String, String>>) context.getSuite().getAttribute("dataOutput");
		} else {
			list = new ArrayList<>();
		}
		list.add(data);
		context.getSuite().setAttribute("dataOutput",list);
		
	}
}
