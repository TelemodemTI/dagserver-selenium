package dagserver.pom.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExecutionDialog {

	protected WebDriver driver;
	
	public ExecutionDialog(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"propertyNotFoundModal\"]")));
	}
	

	public void close() {
		driver.findElement(By.xpath("//*[@id=\"propertyNotFoundModal\"]/div[2]/div/div[3]/button")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"propertyNotFoundModal\"]")));
	}
}
