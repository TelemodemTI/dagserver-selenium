package dagserver.pom.props;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewPropDialog {

	protected WebDriver driver;
	
	public NewPropDialog(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"addPropModal\"]")));
	}

	public void saveNewProp(String name, String descr,String group, String value) {
		driver.findElement(By.xpath("//*[@id=\"namepropinput\"]")).sendKeys(name);
		driver.findElement(By.xpath("//*[@id=\"descrpropinput\"]")).sendKeys(descr);
		driver.findElement(By.xpath("//*[@id=\"grouppropinput\"]")).sendKeys(group);
		driver.findElement(By.xpath("//*[@id=\"valuepropinput\"]")).sendKeys(value);

        driver.findElement(By.xpath("//*[@id=\"addPropModal\"]/div[2]/div/div[3]/button[2]")).click();
	}
}
