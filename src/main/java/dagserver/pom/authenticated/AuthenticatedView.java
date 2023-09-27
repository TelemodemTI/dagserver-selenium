package dagserver.pom.authenticated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dagserver.utils.SeleniumTestException;

public class AuthenticatedView {
	
	protected WebDriver driver;
	private By logoutBtn = By.xpath("//*[@id=\"wrapper\"]/nav/ul[2]/li[3]/ul/li/a");
	private By credentialsLink = By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li/a");
	private By propsLink = By.xpath("//*[@id=\"side-menu\"]/li[2]/ul/li[1]/a");
	
	public AuthenticatedView(WebDriver driver) {
		this.driver = driver;
	}
	public AdminCredentialsView goToCredentials() throws SeleniumTestException {
		WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(credentialsLink));
		driver.findElement(credentialsLink).click();
		return new AdminCredentialsView(driver);
	}
	public void logout() {
		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/nav/ul[2]/li[3]/a")).click();
		WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBtn));
        driver.findElement(logoutBtn).click();
        WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(logoutBtn));
	}
	public PropertiesView goToProps() {
		WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(propsLink));
		driver.findElement(propsLink).click();
		return new PropertiesView(driver);
	}
}
