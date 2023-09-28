package dagserver.pom.authenticated;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dagserver.pom.jobs.ParamsDialog;

public class JobsDetailView {

	protected WebDriver driver;
	private By tabLink = By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/ul/li/a");
	public JobsDetailView(WebDriver driver) {
		this.driver = driver;
	}
	public void selectTab() {
		WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabLink));
		driver.findElement(tabLink).click();
	}
	public ParamsDialog selectStage(String dagname, String stepName) {
		 JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	     String str = "$(\"#canvas-det\").val(\"{\"dagname\":\""+dagname+"\",\"selectedStep\":\""+stepName+"\"});";
	     jsExecutor.executeScript(str);
	     String str2 = "$(\"#canvas-det\").trigger(\"change\");";
	     jsExecutor.executeScript(str2);
	     WebDriverWait wait = new WebDriverWait(driver,3);
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"param-modaljardetailj\"]")));
	     return new ParamsDialog(driver);
	}
}
