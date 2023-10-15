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

public class GithubChannelDialog {

	private WebDriver driver;
	public GithubChannelDialog(WebDriver driver) {
		this.driver = driver;
		WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"myModalLabel\"]")));
	}

	public void createNewWebhook(String string, String string2, String string3, String string4, String string5) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"props-collapser-son\"]")).click();
		WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"collapseNewGithub\"]/div[1]/input")));
        driver.findElement(By.xpath("//*[@id=\"collapseNewGithub\"]/div[1]/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"collapseNewGithub\"]/div[1]/input")).sendKeys(string);
        driver.findElement(By.xpath("//*[@id=\"collapseNewGithub\"]/div[2]/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"collapseNewGithub\"]/div[2]/input")).sendKeys(string2);
        driver.findElement(By.xpath("//*[@id=\"collapseNewGithub\"]/div[3]/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"collapseNewGithub\"]/div[3]/input")).sendKeys(string3);
        Select select = new Select(driver.findElement(By.xpath("//*[@id=\"collapseNewGithub\"]/div[4]/select")));
        select.selectByValue(string4);
        Thread.sleep(3000);
        Select select2 = new Select(driver.findElement(By.xpath("//*[@id=\"collapseNewGithub\"]/div[5]/select")));
        select2.selectByValue(string5);
        driver.findElement(By.xpath("//*[@id=\"collapseNewGithub\"]/button")).click();
        WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"myModalLabel\"]")));
	}

	public List<Map<String, String>> getRepos() {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("github-tbl")));
        WebElement tabla = driver.findElement(By.id("github-tbl"));
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

	public boolean existRepo(String string) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("github-tbl")));
		Boolean found = false;
		var data = this.getRepos();
		for (Iterator<Map<String, String>> iterator = data.iterator(); iterator.hasNext();) {
			Map<String, String> map =  iterator.next();
			if(map.containsValue(string)) {
				found = true;
				break;
			}
		}
		return found;
	}

	public void removeRepo(String string) throws InterruptedException {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("github-tbl")));
        WebElement tabla = driver.findElement(By.id("github-tbl"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement dagColumn = columnas.get(1);
            if(dagColumn.getText().equals(string)) {
            	driver.findElement(By.xpath("//*[@id=\"githubModal\"]/div[2]/div/div[2]/div[2]/table/tbody/tr/td[4]/button")).click();
            	break;
            }
        }
        Thread.sleep(3000);
	}

}
