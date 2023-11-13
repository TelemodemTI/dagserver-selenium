package dagserver.pom.authenticated;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	public DagTabPanelView generate() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        
        WebDriverWait wait = new WebDriverWait(driver,3);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div[2]/button[1]")));
        
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div[1]/div[2]/button[1]")).click();
		return new DagTabPanelView(driver);
	}
	public DagTabPanelView createDefaultDag() throws InterruptedException {
		var tab = this.generate();
		tab.save();
		return tab;
	}
	public DagTabPanelView createCronDag(String dagname, String group, String cronexpr) throws InterruptedException {
		var dagpanel = this.generate();
        dagpanel.setData(dagname, group, cronexpr);
        dagpanel.save();
        return dagpanel;
	}
	public DagTabPanelView createListenerDag(String dagname, String group, String listener, String targetType, String targetname) throws InterruptedException {
		
		var dagpanel = this.generate();

        driver.findElement(By.xpath("//*[contains(@id,'dagnameinput-')]")).clear();
        driver.findElement(By.xpath("//*[contains(@id,'dagnameinput-')]")).sendKeys(dagname);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@id,'canvas-')]/a")).click();
        Thread.sleep(1000);
        
        driver.findElement(By.xpath("//*[contains(@id,'props-collapser')]/a")).click();
        driver.findElement(By.xpath("//*[contains(@id,'props-collapser')]/a")).click();
        
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
        
        Thread.sleep(3000);
        Select select = new Select(driver.findElement(By.xpath("//*[contains(@id,'dagtargetinput-')]")));
        select.selectByValue(targetname);
        return dagpanel;
        
	}
	public void addDummyStep(String stepname,String dagname) throws InterruptedException {
		
		try {
			WebDriverWait wait2 = new WebDriverWait(driver,3);
	        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[id='stepinput-"+dagname+"']")));	
		} catch (Exception e) {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"props-collapser-son-"+dagname+"\"]")).click();
		}
		Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='stepinput-"+dagname+"']")).clear();
        driver.findElement(By.xpath("//*[@id='stepinput-"+dagname+"']")).sendKeys(stepname);
        driver.findElement(By.xpath("//*[@id='collapseOne"+dagname+"']/div/div[4]/button[1]")).click();
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
	public void goToTop() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, 0);"); 
	}
	public void save() throws InterruptedException {
		this.goToTop();
		By button = By.xpath("//*[@id=\"save-jar-btn\"]");
		WebDriverWait wait = new WebDriverWait(driver,3);
	    wait.until(ExpectedConditions.elementToBeClickable(button));
		driver.findElement(button).click();
	}
	public void addStep(String dagname,String step1, String string) throws InterruptedException {
		try {
			WebDriverWait wait2 = new WebDriverWait(driver,3);
	        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'stepinput-')]")));	
		} catch (Exception e) {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"props-collapser-son\"]")).click();
		}
		Thread.sleep(3000);
		WebElement combo = driver.findElement(By.xpath("//*[@id=\"steptype-"+dagname+"\"]"));
		Select select = new Select(combo);
		select.selectByValue(string);
		driver.findElement(By.xpath("//*[@id=\"collapseOne"+dagname+"\"]/div/a")).click();		
	}
}
