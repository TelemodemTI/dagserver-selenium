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
import dagserver.pom.props.EditPropDialog;
import dagserver.pom.props.ImportPropDialog;
import dagserver.pom.props.NewPropDialog;

public class PropertiesView {
	
	protected WebDriver driver;
	public PropertiesView(WebDriver driver) {
		this.driver = driver;
	}

	public List<Map<String, String>> getActualProps() {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-props")));
        WebElement tabla = driver.findElement(By.id("dataTables-props"));
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
	
	public Boolean existProp(String propkey) {
		Boolean found = false;
		var data = this.getActualProps();
		for (Iterator<Map<String, String>> iterator = data.iterator(); iterator.hasNext();) {
			Map<String, String> map =  iterator.next();
			if(map.containsValue(propkey)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	public void deleteProp(String username) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-props")));
        WebElement tabla = driver.findElement(By.id("dataTables-props"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement userColumn = columnas.get(1);
            if(userColumn.getText().equals(username)) {
            	driver.findElement(By.xpath("//*[@id=\"dataTables-props\"]/tbody/tr["+i+"]/td[5]/button[1]")).click();
            	break;
            }
        }
	}
	public void deleteByGroup(String username) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-props")));
        WebElement tabla = driver.findElement(By.id("dataTables-props"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement userColumn = columnas.get(1);
            if(userColumn.getText().equals(username)) {
            	driver.findElement(By.xpath("//*[@id=\"dataTables-props\"]/tbody/tr["+i+"]/td[5]/button[2]")).click();
            	break;
            }
        }
	}
	
	public void showProp(String username) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-props")));
        WebElement tabla = driver.findElement(By.id("dataTables-props"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement userColumn = columnas.get(1);
            if(userColumn.getText().equals(username)) {
            	driver.findElement(By.xpath("//*[@id=\"dataTables-props\"]/tbody/tr["+i+"]/td[5]/button[3]")).click();
            	break;
            }
        }
	}
	
	public EditPropDialog editProp(String username) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-props")));
        WebElement tabla = driver.findElement(By.id("dataTables-props"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement userColumn = columnas.get(1);
            if(userColumn.getText().equals(username)) {
            	driver.findElement(By.xpath("//*[@id=\"dataTables-props\"]/tbody/tr["+i+"]/td[5]/button[4]")).click();
            	return new EditPropDialog(driver);
            }
        }
		return null;
	}
	
	public void search(String propkey) throws InterruptedException {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dataTables-props_filter\"]/label/input")));
		driver.findElement(By.xpath("//*[@id=\"dataTables-props_filter\"]/label/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"dataTables-props_filter\"]/label/input")).sendKeys(propkey);
		Thread.sleep(3000);
	}
	
	public void exportSelected() {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/button[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/a")).click();
	}
	
	public NewPropDialog addNewProp() {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/button[1]")).click();
		return new NewPropDialog(driver);
	}
	
	public ImportPropDialog importProp() {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/button[3]")).click();
		return new ImportPropDialog(driver);
		
	}
}
