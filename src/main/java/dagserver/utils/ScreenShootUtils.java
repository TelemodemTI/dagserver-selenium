package dagserver.utils;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Random;

public class ScreenShootUtils {

	public byte[] takeScreenShotAllPage(WebDriver driver,By xpathbody) throws IOException {
		WebElement element = driver.findElement(xpathbody  );
		byte[] sourceFile = element.getScreenshotAs(OutputType.BYTES);
		return sourceFile;
	}
	public static String generateIdentifier(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder cadenaAleatoria = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int indiceAleatorio = random.nextInt(caracteres.length());
            char caracterAleatorio = caracteres.charAt(indiceAleatorio);
            cadenaAleatoria.append(caracterAleatorio);
        }
        return cadenaAleatoria.toString();
    }
	
}
