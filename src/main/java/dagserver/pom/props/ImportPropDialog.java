package dagserver.pom.props;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImportPropDialog {

	protected WebDriver driver;
	
	public ImportPropDialog(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"importPropertiesModal\"]")));
	}

	public void importNewProp(String file) {
		driver.findElement(By.xpath("//*[@id=\"importPropertiesModal\"]/div[2]/div/div[2]/div/input")).sendKeys(file);
        driver.findElement(By.xpath("//*[@id=\"importPropertiesModal\"]/div[2]/div/div[3]/button[2]")).click();
	}
}
