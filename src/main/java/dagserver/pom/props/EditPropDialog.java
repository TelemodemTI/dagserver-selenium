package dagserver.pom.props;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditPropDialog {

	protected WebDriver driver;
	
	public EditPropDialog(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"value-inputer\"]")));
	}

	public void saveNewValue(String name, String descr,String group, String value) {
		driver.findElement(By.xpath("//*[@id=\"value-inputer\"]/div[2]/div/div[2]/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"value-inputer\"]/div[2]/div/div[2]/div/input")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id=\"value-inputer\"]/div[2]/div/div[3]/button[2]")).click();
	}
}
