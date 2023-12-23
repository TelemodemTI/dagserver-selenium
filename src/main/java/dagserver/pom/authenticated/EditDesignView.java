package dagserver.pom.authenticated;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dagserver.pom.jobs.ParamsDialogNewJob;
import dagserver.pom.jobs.RenameJarDialog;
import dagserver.pom.jobs.ResultDialog;

public class EditDesignView {

	protected WebDriver driver;
	protected Boolean opened = false;
	protected By jarname = By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div[1]/div/input");
	protected By createDagButton = By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div[2]/button[1]");
	public EditDesignView(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[1]")));
	}
	public ResultDialog execute() {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div/button[4]")).click();
		if(isAlertPresent()) {
			driver.switchTo().alert().accept();
			return null;
		} else {
			WebDriverWait wait2 = new WebDriverWait(driver,30);
			wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"result-step-modal\"]/div[2]/div/div[2]/div[1]")));
	        return new ResultDialog(driver);
		}
	}
	public void selectDag(String dagname) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"canvas-"+dagname+"\"]/a")).click();
		Thread.sleep(1000);
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
	public RenameJarDialog rename() {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div/button[3]")).click();
		return new RenameJarDialog(driver);
	} 
	public ParamsDialogNewJob selectStage(String stepName,String dagname) throws InterruptedException {
		 driver.findElement(By.xpath("//*[@id=\"canvas-"+dagname+"\"]/a"));
		 Thread.sleep(1000);
		 JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	     String str = "$(\"#canvas-new-det\").val(\"{\\\"stepname\\\":\\\""+stepName+"\\\"}\");";
	     jsExecutor.executeScript(str);
	     String str2 = "$(\"#canvas-new-det\").trigger(\"change\");";
	     jsExecutor.executeScript(str2);
	     WebDriverWait wait = new WebDriverWait(driver,3);
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"param-modalexistingj\"]")));
	     return new ParamsDialogNewJob(driver);
	}
	public void addStep(String dagname,String step1, String string) throws InterruptedException {
		if(opened == false) {
			driver.findElement(By.xpath("//*[@id=\"props-collapser-son-"+dagname+"\"]")).click();
			opened = true;
		}
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"stepinput-"+dagname+"\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"stepinput-"+dagname+"\"]")).sendKeys(step1);
		WebElement combo = driver.findElement(By.xpath("//*[@id=\"steptype-"+dagname+"\"]"));
		Select select = new Select(combo);
		select.selectByValue(string);
		driver.findElement(By.xpath("//*[@id=\"collapseOne"+dagname+"\"]/div/a")).click();		
	}
	public void save() throws InterruptedException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver,3);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div/button[2]")));
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div/button[2]")).click();
		Thread.sleep(2000);
	}
}
