package dagserver.pom.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultDialog {

	protected WebDriver driver;
	
	public ResultDialog(WebDriver driver) {
		this.driver = driver;
		try {
			WebDriverWait wait2 = new WebDriverWait(driver,10);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"result-step-modal\"]/div[2]/div/div[2]/div[1]")));	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	public void close() {
		driver.findElement(By.xpath("//*[@id=\"result-step-modal\"]/div[2]/div/div[2]/div[3]/button")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"result-step-modal\"]")));
	}

	public String getOutputXcom(String step1) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"accordion-step1\"]/div/div[1]/h4/a")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"accordion-step1\"]/div/div[1]/h4/a")));
        return driver.findElement(By.xpath("//*[@id=\"collapseOne-step1\"]/div/div")).getText();
	}
}
