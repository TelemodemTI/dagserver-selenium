package dagserver.pom.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParamsDialogNewJob {

	protected WebDriver driver;
	
	public ParamsDialogNewJob(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"param-modalexistingj\"]")));
	}

	public void close() {
		driver.findElement(By.xpath("//*[@id=\"param-modalexistingj\"]/div[2]/div/div[3]/button[2]")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"param-modalexistingj\"]")));
	}
	
	public void remove() {
		driver.findElement(By.xpath("//*[@id=\"param-modalexistingj\"]/div[2]/div/div[3]/button[1]")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"param-modalexistingj\"]")));
	}
	public ResultDialog test() {
		driver.findElement(By.xpath("//*[@id=\"param-modalexistingj\"]/div[2]/div/div[2]/div[1]/button[2]")).click();
		if(this.isAlertPresent()) {
			driver.switchTo().alert().accept();
			WebDriverWait wait2 = new WebDriverWait(driver,3);
	        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"result-step-modal\"]")));
	        return new ResultDialog(driver);
		} else {
			return null;
		}
		
	}
	private boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}
}
