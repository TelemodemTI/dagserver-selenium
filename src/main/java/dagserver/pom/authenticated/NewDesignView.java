package dagserver.pom.authenticated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewDesignView {

	protected WebDriver driver;
	protected By jarname = By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/button");
	public NewDesignView(WebDriver driver) {
		this.driver = driver;
	}
	public void setName(String name) {
		driver.findElement(jarname).clear();
		driver.findElement(jarname).sendKeys(name);
	}
}
