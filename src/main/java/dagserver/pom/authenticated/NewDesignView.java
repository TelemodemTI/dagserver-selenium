package dagserver.pom.authenticated;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import dagserver.pom.jobs.ParamsDialogNewJob;

public class NewDesignView {

	protected WebDriver driver;
	protected By jarname = By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div[1]/div/input");
	protected By createDagButton = By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div[2]/button[1]");
	public NewDesignView(WebDriver driver) {
		this.driver = driver;
	}
	public void setName(String name) {
		driver.findElement(jarname).clear();
		driver.findElement(jarname).sendKeys(name);
	}
	public void createCronDag(String dagname, String group, String cronexpr) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div[2]/button[1]")).click();
		
		WebDriverWait wait2 = new WebDriverWait(driver,5);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'dagnameinput-')]")));
        
        
        driver.findElement(By.xpath("//*[contains(@id,'dagnameinput-')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id,'dagnameinput-')]")).sendKeys(dagname);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@id,'canvas-')]/a")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"props-collapser\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"props-collapser\"]")).click();
        Thread.sleep(1000);
        

        
        driver.findElement(By.xpath("//*[contains(@id,'daggroupinput-')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id,'daggroupinput-')]")).sendKeys(group);
        driver.findElement(By.xpath("//*[contains(@id,'dagcroninput-')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id,'dagcroninput-')]")).sendKeys(cronexpr);
        driver.findElement(By.xpath("//*[contains(@id,'collapseOne')]/div[4]/button[1]")).click();
	}
	public void createListenerDag(String dagname, String group, String listener, String targetType, String targetname) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div[2]/button[1]")).click();
		
		WebDriverWait wait2 = new WebDriverWait(driver,5);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'dagnameinput-')]")));
        
        driver.findElement(By.xpath("//*[contains(@id,'dagnameinput-')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id,'dagnameinput-')]")).sendKeys(dagname);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@id,'canvas-')]/a")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"props-collapser\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"props-collapser\"]")).click();
        Thread.sleep(1000);
        
        
        driver.findElement(By.xpath("//*[contains(@id,'daggroupinput-')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id,'daggroupinput-')]")).sendKeys(group);
        
        driver.findElement(By.xpath("//*[@id=\"listener-type-link\"]")).click();
        Thread.sleep(1000);
        
        if(listener.equals("onStart")) {
        	driver.findElement(By.xpath("//*[@id=\"optionslistenerLoc1\"]")).click();
        } else {
        	driver.findElement(By.xpath("//*[@id=\"optionslistenerLoc2\"]")).click();
        }
        Thread.sleep(1000);
        
        if(targetType.equals("DAG")) {
        	driver.findElement(By.xpath("//*[@id=\"optionslistenerGroup1\"]")).click();
        } else {
        	driver.findElement(By.xpath("//*[@id=\"optionslistenerGroup2\"]")).click();
        }
        Thread.sleep(1000);
        

        Select select = new Select(driver.findElement(By.xpath("//*[contains(@id,'dagtargetinput-')]")));
        select.selectByValue(targetname);
        
        driver.findElement(By.xpath("//*[contains(@id,'collapseOne')]/div[4]/button[1]")).click();
	}
	public void addDummyStep(String stepname) throws InterruptedException {
		
		try {
			WebDriverWait wait2 = new WebDriverWait(driver,3);
	        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'stepinput-')]")));	
		} catch (Exception e) {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"props-collapser-son\"]")).click();
		}
		Thread.sleep(3000);
        driver.findElement(By.xpath("//*[contains(@id,'stepinput-')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id,'stepinput-')]")).sendKeys(stepname);
        driver.findElement(By.xpath("//*[contains(@id,'collapseOne')]/div/div[4]/button[1]")).click();
	}
	
	public ParamsDialogNewJob selectStage(String stepName) {
		 JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	     String str = "$(\"#canvas-new-det\").val(\"{\\\"stepname\\\":\\\""+stepName+"\\\"}\");";
	     jsExecutor.executeScript(str);
	     String str2 = "$(\"#canvas-new-det\").trigger(\"change\");";
	     jsExecutor.executeScript(str2);
	     WebDriverWait wait = new WebDriverWait(driver,3);
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"param-modalexistingj\"]")));
	     return new ParamsDialogNewJob(driver);
	}
	public void save() {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div[2]/button[2]")).click();
	}
}
