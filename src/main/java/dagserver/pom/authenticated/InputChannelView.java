package dagserver.pom.authenticated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dagserver.pom.channels.GithubChannelDialog;
import dagserver.pom.channels.RabbitChannelDialog;
import dagserver.pom.channels.RedisChannelDialog;

public class InputChannelView {

	protected WebDriver driver;
	private By cred = By.xpath("//*[@id=\"page-wrapper\"]/div/div[1]/div/h1"); 
	public InputChannelView(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cred));
	}
	public GithubChannelDialog githubModal() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[3]/button")).click();
		return new GithubChannelDialog(driver);
	}
	public RabbitChannelDialog rabbitModal() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div/table/tbody/tr[3]/td[3]/button")).click();
		return new RabbitChannelDialog(driver);
	}
	public RedisChannelDialog redisModal() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/div/table/tbody/tr[4]/td[3]/button")).click();
		return new RedisChannelDialog(driver);
	}
	
}
