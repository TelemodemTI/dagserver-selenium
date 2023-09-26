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

import dagserver.pom.credentials.NewUserDialog;
import dagserver.utils.SeleniumTestException;

public class AdminCredentialsView {

	protected WebDriver driver;
	
	private By title = By.xpath("//*[@id=\"page-wrapper\"]/div/div[1]/div/h1");
	
	public AdminCredentialsView(WebDriver driver) throws SeleniumTestException {
		this.driver = driver;
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(title));
        if(!driver.findElement(title).getText().equals("Credentials")) {
        	throw new SeleniumTestException("Error al ir a seccion Admin/Credentials");
        }
	}
	
	public void deleteUser(String username) {
		WebDriverWait wait2 = new WebDriverWait(driver,3);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-credentials")));
        WebElement tabla = driver.findElement(By.id("dataTables-credentials"));
        List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        for (int i = 1; i < filas.size(); i++) {
            WebElement fila = filas.get(i);
            List<WebElement> columnas = fila.findElements(By.tagName("td"));
            WebElement userColumn = columnas.get(1);
            if(userColumn.getText().equals(username)) {
            	driver.findElement(By.xpath("//*[@id=\"dataTables-credentials\"]/tbody/tr["+i+"]/td[4]/button")).click();
            	break;
            }
        }
	}
	
	public Boolean existCredential(String newusername) {
		Boolean found = false;
		var data = this.getActualCredentials();
		for (Iterator<Map<String, String>> iterator = data.iterator(); iterator.hasNext();) {
			Map<String, String> map =  iterator.next();
			if(map.containsValue(newusername)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	public List<Map<String, String>> getActualCredentials() {
			WebDriverWait wait2 = new WebDriverWait(driver,3);
	        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dataTables-credentials")));
	        WebElement tabla = driver.findElement(By.id("dataTables-credentials"));
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
	
	public NewUserDialog addUser() {
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/div/div/div[2]/button")).click();
		return new NewUserDialog(driver);
	}
}
