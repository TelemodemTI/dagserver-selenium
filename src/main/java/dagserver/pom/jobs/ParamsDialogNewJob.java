package dagserver.pom.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParamsDialogNewJob {

	protected WebDriver driver;
	
	public ParamsDialogNewJob(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"param-modalexistingj\"]")));
	}

	public void close() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"param-modalexistingj\"]/div[2]/div/div[3]/button[2]")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"param-modalexistingj\"]")));
        Thread.sleep(3000);
	}
	
	public void remove() {
		driver.findElement(By.xpath("//*[@id=\"param-modalexistingj\"]/div[2]/div/div[3]/button[1]")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"param-modalexistingj\"]")));
	}
	public ResultDialog test() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"param-modalexistingj\"]/div[2]/div/div[2]/div[1]/button[2]")).click();
		if(this.isAlertPresent()) {
			
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			
			WebDriverWait wait2 = new WebDriverWait(driver,3);
			wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"result-step-modal\"]/div[2]/div/div[2]/div[1]")));
			
	        return new ResultDialog(driver);
		} else {
			return null;
		}
		
	}
	public void save() {
		driver.findElement(By.xpath("//*[@id=\"param-modalexistingj\"]/div[2]/div/div[3]/button[3]")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"param-modalexistingj\"]")));
	}
	public void sendParameter(String name, String value, String type) {
		String inputName = "//*[@id=\"param-"+name+"-value\"]";
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath(inputName)));
		if(type.equals("input")) {
			driver.findElement(By.xpath(inputName)).clear();
			driver.findElement(By.xpath(inputName)).sendKeys(value);	
		} else if(type.equals("list")) {
			Select select = new Select(driver.findElement(By.xpath(inputName)));
	        select.selectByValue(value);
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

	public void sendScript(String script) {
		driver.findElement(By.xpath("//*[@id=\"trigger-id\"]")).click();
        JavascriptExecutor js = (JavascriptExecutor)driver;
	    String str = "$(\"#canvas-codemirror-new-det\").val(\""+script+"\");";
	    js.executeScript(str);
	    String str2 = "$(\"#canvas-codemirror-new-det\").trigger(\"change\");";
	    js.executeScript(str2);
	}
}
