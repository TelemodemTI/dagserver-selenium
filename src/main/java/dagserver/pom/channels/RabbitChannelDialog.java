package dagserver.pom.channels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RabbitChannelDialog {

	private WebDriver driver;
	public RabbitChannelDialog(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rabbitModal\"]")));
	}

	public void configureRabbit(String host, String port,String user, String pwd) throws InterruptedException {
		
		driver.findElement(By.xpath("//*[@id=\"rabbitModal\"]/div[2]/div/div[2]/div[1]/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"rabbitModal\"]/div[2]/div/div[2]/div[1]/input")).sendKeys(host);
		
        driver.findElement(By.xpath("//*[@id=\"rabbitModal\"]/div[2]/div/div[2]/div[2]/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"rabbitModal\"]/div[2]/div/div[2]/div[2]/input")).sendKeys(port);
		
        driver.findElement(By.xpath("//*[@id=\"rabbitModal\"]/div[2]/div/div[2]/div[3]/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"rabbitModal\"]/div[2]/div/div[2]/div[3]/input")).sendKeys(user);
        
        driver.findElement(By.xpath("//*[@id=\"rabbitModal\"]/div[2]/div/div[2]/div[4]/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"rabbitModal\"]/div[2]/div/div[2]/div[4]/input")).sendKeys(pwd);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"rabbitModal\"]/div[2]/div/div[2]/button")).click();

	}

	public List<Map<String, String>> getQueues() {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("rabbit-tbl")));
        WebElement tabla = driver.findElement(By.id("rabbit-tbl"));
        List<Map<String, String>> datosTabla = new ArrayList<>();
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        List<WebElement> titulos = driver.findElements(By.tagName("th"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            
            Map<String, String> filaDatos = new HashMap<>();
            for (int j = 0; j < columnas.size(); j++) {
                WebElement columna = columnas.get(j);
                WebElement titulo = titulos.get(j);
                String nombreColumna = titulo.getText(); 
                String valorCelda = columna.getText();
                filaDatos.put(nombreColumna, valorCelda);
            }	            
            datosTabla.add(filaDatos);
        }
        return datosTabla;
	}

	public boolean existQueue(String string) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("rabbit-tbl")));
		Boolean found = false;
		var data = this.getQueues();
		for (Iterator<Map<String, String>> iterator = data.iterator(); iterator.hasNext();) {
			Map<String, String> map =  iterator.next();
			if(map.containsValue(string)) {
				found = true;
				break;
			}
		}
		return found;
	}

	public void removeQueue(String string) throws InterruptedException {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("rabbit-tbl")));
        WebElement tabla = driver.findElement(By.id("rabbit-tbl"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement dagColumn = columnas.get(0);
            if(dagColumn.getText().equals(string)) {
            	driver.findElement(By.xpath("//*[@id=\"rabbit-tbl\"]/tbody/tr/td[4]/button")).click();
            	break;
            }
        }
        Thread.sleep(3000);
	}

	public void addQueue(String string, String string2, String string3) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"props-collapser-son-1\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"collapseNewQueue\"]/div[1]/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"collapseNewQueue\"]/div[1]/input")).sendKeys(string);
		
		Select select = new Select(driver.findElement(By.xpath("//*[@id=\"collapseNewQueue\"]/div[2]/select")));
        select.selectByValue(string2);
        Thread.sleep(3000);
        Select select2 = new Select(driver.findElement(By.xpath("//*[@id=\"collapseNewQueue\"]/div[3]/select")));
        select2.selectByValue(string3);
        driver.findElement(By.xpath("//*[@id=\"collapseNewQueue\"]/button")).click();
	}

}
