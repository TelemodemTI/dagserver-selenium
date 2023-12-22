package dagserver.test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;

import dagserver.pom.authenticated.AuthenticatedView;
import dagserver.pom.authenticated.PropertiesView;
import dagserver.pom.login.LoginForm;
import dagserver.utils.BaseTest;

public class PropsTest extends BaseTest {

		@Test
		@Parameters({"url","username","pwd","propkey","propval","propgroup","filename"})
		void propsTest(String url,String username, String pwd, String propkey, String propval, String propgroup,String filename,ITestContext context) throws InterruptedException, IOException {
			String uploadFileReal = "";
			if(this.application.getProperty("driver.mode").equals("DOCKER")) {
				uploadFileReal = "/statics/"+filename;
			} else {
				uploadFileReal = Paths.get("statics").toAbsolutePath().toString() + "/" +filename;	
			}
			
			driver.get(url);
	    	LoginForm login = new LoginForm(driver);
	    	if(login.login(username, pwd)) {
	    		AuthenticatedView autenticado = new AuthenticatedView(driver);
	    		PropertiesView view = autenticado.goToProps();
	    		view.search(propkey);
	    		if(view.existProp(propkey)) {
	    			view.deleteProp(propkey);
	    			view = autenticado.goToProps();
	    		}
	    		var newpropForm = view.addNewProp();
	    		newpropForm.saveNewProp(propkey, "descr", propgroup, propval);
	    		view = autenticado.goToProps();
	    		view.search(propkey);
	    		if(view.existProp(propkey)) {
	    			view.showProp(propkey);
	    			var model = view.editProp(propkey);
	    			model.saveNewValue(propkey, "editrado", propgroup, propval);
	    			view = autenticado.goToProps();
	    			view.search(propkey);
	    			view.exportSelected();
	    			view.search("");
	    			view.deleteProp(propkey);
	    			view = autenticado.goToProps();
	    			var modalimport = view.importProp();
	    			modalimport.importNewProp(uploadFileReal);
	    			view = autenticado.goToProps();
	    			view.deleteByGroup(propgroup);
	    			this.writeEvidence(context,"propsTest","OK",By.xpath("/html/body"));
	    			assertTrue(true);
	    		} else {
	    			this.writeEvidence(context,"propsTest","ERROR",By.xpath("/html/body"));
	    			assertTrue(false,"problema en vista de propiedades");
	    		}
	    	} else {
	    		this.writeEvidence(context,"propsTest","ERROR",By.xpath("/html/body"));
	    		assertTrue(false,"problema en login?");
	    	}
		}
}
