package dagserver.pom.login;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginForm {

	protected WebDriver driver;
	
	public LoginForm(WebDriver driver) {
		this.driver = driver;
	}
	
	public Boolean login(String username, String pwd) throws TimeoutException  {
		WebElement inputElement = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div/div/div[2]/fieldset[1]/div[1]/input"));
        inputElement.sendKeys(username);
        WebElement pwdElement = driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div/div/div[2]/fieldset[1]/div[2]/input"));
        pwdElement.sendKeys(pwd);
        driver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div/div/div[2]/fieldset[1]/button")).click();
        try {
        	WebDriverWait wait = new WebDriverWait(driver,3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wrapper\"]/nav/ul[1]/li/a")));
            return true;
		} catch (Exception e) {
			WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-login/div/div/div/div/div[2]/fieldset[1]/div[3]")));
            return false;
		}
        
	}
}
