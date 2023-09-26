package dagserver.test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dagserver.pom.authenticated.AuthenticatedView;
import dagserver.pom.login.LoginForm;
import dagserver.utils.BaseTest;

public class LoginTest extends BaseTest {
	
    @Test
    @Parameters({"url","username","pwd"})  
    void loginOk(String url,String username, String pwd) {
    	driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		assertTrue(true);
    	} else {
    		assertTrue(false,"login incorrecto");
    	}
    }
    @Test
    @Parameters({"url","username","pwd"})  
    void logoutOk(String url,String username, String pwd) {
    	driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		autenticado.logout();
    		LoginForm login2 = new LoginForm(driver);
    		assertNotNull(login2);
    	} else {
    		assertTrue(false,"login incorrecto");
    	}
    }
    @Test
    @Parameters({"url","username","pwd"})  
    void loginNOK(String url,String username, String pwd) {
    	driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		assertTrue(false,"login incorrecto");
    	} else {
    		assertTrue(true);
    	}
    }

}