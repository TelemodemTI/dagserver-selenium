package dagserver.test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import dagserver.pom.authenticated.AuthenticatedView;
import dagserver.pom.login.LoginForm;
import dagserver.utils.BaseTest;

public class LoginTest extends BaseTest {
	
    @Test
    @Parameters({"url","username","pwd"})  
    void loginOk(String url,String username, String pwd, ITestContext context) throws IOException {
    	driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		this.writeEvidence(context,"loginOk","OK",By.xpath("/html/body"));
    		assertTrue(true);
    	} else {
    		this.writeEvidence(context,"loginOk","ERROR",By.xpath("/html/body"));
    		assertTrue(false,"login incorrecto");
    	}
    }
    

	@Test
    @Parameters({"url","username","pwd"})  
    void logoutOk(String url,String username, String pwd,ITestContext context) throws IOException {
    	driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		autenticado.logout();
    		LoginForm login2 = new LoginForm(driver);
    		this.writeEvidence(context,"logoutOk","OK",By.xpath("/html/body"));
    		assertNotNull(login2);
    	} else {
    		this.writeEvidence(context,"logoutOk","ERROR",By.xpath("/html/body"));
    		assertTrue(false,"login incorrecto");
    	}
    }
    @Test
    @Parameters({"url","username","pwd"})  
    void loginNOK(String url,String username, String pwd,ITestContext context) throws IOException {
    	driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		this.writeEvidence(context,"loginNOK","ERROR",By.xpath("/html/body"));
    		assertTrue(false,"login incorrecto");
    	} else {
    		this.writeEvidence(context,"loginNOK","OK",By.xpath("/html/body"));
    		assertTrue(true);
    	}
    }

}