package dagserver.pom.authenticated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dagserver.pom.jobs.ImportJarDialog;

public class JobsView {

	protected WebDriver driver;
	public JobsView(WebDriver driver) {
		this.driver = driver;
	}
	public void selectCompiledTab() {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/ul/li[1]/a")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-jobs")));
	}
	public void selectDesigndTab() {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/ul/li[2]/a")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-uncompiledjobs")));
	}
	
	public List<Map<String, String>> getActualDesigns() {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-uncompiledjobs")));
        WebElement tabla = driver.findElement(By.id("dataTables-uncompiledjobs"));
        List<Map<String, String>> datosTabla = new ArrayList<>();
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        List<WebElement> titulos = tabla.findElements(By.tagName("th"));
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
	
	public List<Map<String, String>> getActualJobs() {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-jobs")));
        WebElement tabla = driver.findElement(By.id("dataTables-jobs"));
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
	public Boolean existDesign(String propkey) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-uncompiledjobs")));
		Boolean found = false;
		var data = this.getActualDesigns();
		for (Iterator<Map<String, String>> iterator = data.iterator(); iterator.hasNext();) {
			Map<String, String> map =  iterator.next();
			if(map.containsValue(propkey)) {
				found = true;
				break;
			}
		}
		return found;
	}
	public Boolean existJob(String propkey) {
		Boolean found = false;
		var data = this.getActualJobs();
		for (Iterator<Map<String, String>> iterator = data.iterator(); iterator.hasNext();) {
			Map<String, String> map =  iterator.next();
			if(map.containsValue(propkey)) {
				found = true;
				break;
			}
		}
		return found;
	}
	public void selectOption(String dagname, Integer botonIndex) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-jobs")));
        WebElement tabla = driver.findElement(By.id("dataTables-jobs"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement dagColumn = columnas.get(3);
            if(dagColumn.getText().equals(dagname)) {
            	driver.findElement(By.xpath("//*[@id=\"dataTables-jobs\"]/tbody/tr["+i+"]/td[6]/button["+botonIndex+"]")).click();
            	break;
            }
        }
	}
	
	public NewDesignView newJobForm() {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/a")).click();
		return new NewDesignView(driver); 
	}
	
	public void deleteDesign(String jarname) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-uncompiledjobs")));
        WebElement tabla = driver.findElement(By.id("dataTables-uncompiledjobs"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement userColumn = columnas.get(1);
            if(userColumn.getText().equals(jarname)) {
            	driver.findElement(By.xpath("//*[@id=\"dataTables-uncompiledjobs\"]/tbody/tr["+i+"]/td[4]/button[4]")).click();
            	break;
            }
        }
	}
	public void compileDesign(String jarname) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-uncompiledjobs")));
        WebElement tabla = driver.findElement(By.id("dataTables-uncompiledjobs"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement userColumn = columnas.get(1);
            if(userColumn.getText().equals(jarname)) {
            	driver.findElement(By.xpath("//*[@id=\"dataTables-uncompiledjobs\"]/tbody/tr["+i+"]/td[4]/button[3]")).click();
            	break;
            }
        }
	}
	public void exportDesign(String jarname) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-uncompiledjobs")));
        WebElement tabla = driver.findElement(By.id("dataTables-uncompiledjobs"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement userColumn = columnas.get(1);
            if(userColumn.getText().equals(jarname)) {
            	driver.findElement(By.xpath("//*[@id=\"dataTables-uncompiledjobs\"]/tbody/tr["+i+"]/td[4]/button[2]")).click();
            	break;
            }
        }
	}
	public EditDesignView editDesign(String jarname) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-uncompiledjobs")));
        WebElement tabla = driver.findElement(By.id("dataTables-uncompiledjobs"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement userColumn = columnas.get(1);
            if(userColumn.getText().equals(jarname)) {
            	driver.findElement(By.xpath("//*[@id=\"dataTables-uncompiledjobs\"]/tbody/tr["+i+"]/td[4]/button[1]")).click();
            	return new EditDesignView(driver);
            }
        }
		return null;
	}
	
	public ImportJarDialog importJar() {
		driver.findElement(By.xpath("//*[@id=\"templates\"]/app-uncompiled-tab/button")).click();
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("importUncompiledModal")));
        return new ImportJarDialog(driver);
	}
}
