package dagserver.pom.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RenameJarDialog {

	protected WebDriver driver;
	
	public RenameJarDialog(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"value-inputer\"]")));
	}

	public void save(String newname) {
		
		driver.findElement(By.xpath("//*[@id=\"value-inputer\"]/div[2]/div/div[2]/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"value-inputer\"]/div[2]/div/div[2]/div/input")).sendKeys(newname);
		driver.findElement(By.xpath("//*[@id=\"value-inputer\"]/div[2]/div/div[3]/button[2]")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"value-inputer\"]")));
	}
	
	public void close() {
		driver.findElement(By.xpath("//*[@id=\"result-step-modal\"]/div[2]/div/div[2]/div[3]/button")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"result-step-modal\"]")));
	}
}
