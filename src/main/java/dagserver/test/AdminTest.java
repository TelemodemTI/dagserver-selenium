package dagserver.test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import dagserver.pom.authenticated.AdminCredentialsView;
import dagserver.pom.authenticated.AuthenticatedView;
import dagserver.pom.credentials.NewUserDialog;
import dagserver.pom.login.LoginForm;
import dagserver.utils.BaseTest;
import dagserver.utils.SeleniumTestException;

public class AdminTest extends BaseTest {
	
	@Test
    @Parameters({"url","username","pwd","newusername","newpwd","newaccountType"})  
    void createUser(String url,String username, String pwd,String newusername, String newpwd, String newaccountType,ITestContext context) throws SeleniumTestException, IOException {
		driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		AdminCredentialsView credentials = autenticado.goToCredentials();
    		if(credentials.existCredential(newusername)) {
    			credentials.deleteUser(newusername);
    			credentials = autenticado.goToCredentials();
    		}
    		NewUserDialog newuserDialog = credentials.addUser();
    		newuserDialog.saveNewUser(newusername, newpwd, newaccountType);
    		credentials = autenticado.goToCredentials();
    		if(credentials.existCredential(newusername)) {
    			this.writeEvidence(context,"createUser","OK",By.xpath("/html/body"));
    			assertTrue(true);
    		} else {
    			this.writeEvidence(context,"createUser","ERROR",By.xpath("/html/body"));
    			assertTrue(false,"Cuenta de usuario no agregada");
    		}
    	}
	}
	
	@Test
	@Parameters({"url","username","pwd","newusername","newpwd","newaccountType"}) 
	void deleteUserTest(String url,String username, String pwd,String newusername, String newpwd, String newaccountType,ITestContext context) throws SeleniumTestException, IOException {
		driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		AdminCredentialsView credentials = autenticado.goToCredentials();
    		if(credentials.existCredential(newusername)) {
    			credentials.deleteUser(newusername);
    			credentials = autenticado.goToCredentials();
    		}
    		NewUserDialog newuserDialog = credentials.addUser();
    		newuserDialog.saveNewUser(newusername, newpwd, newaccountType);
    		credentials = autenticado.goToCredentials();
    		if(credentials.existCredential(newusername)) {
    			credentials.deleteUser(newusername);
    			credentials = autenticado.goToCredentials();
    			this.writeEvidence(context,"deleteUser","OK",By.xpath("/html/body"));
    			assertTrue(true);
    		} else {
    			this.writeEvidence(context,"deleteUser","ERROR",By.xpath("/html/body"));
    			assertTrue(false,"Cuenta de usuario no agregada");
    		}
    	}
	}
}
