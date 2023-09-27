package dagserver.test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Parameters;

import dagserver.pom.authenticated.AuthenticatedView;
import dagserver.pom.authenticated.PropertiesView;
import dagserver.pom.login.LoginForm;
import dagserver.utils.BaseTest;

public class PropsTest extends BaseTest {

		@Test
		@Parameters({"url","username","pwd","propkey","propval","propgroup","filename"})
		void propsTest(String url,String username, String pwd, String propkey, String propval, String propgroup,String filename) throws InterruptedException {
			driver.get(url);
	    	LoginForm login = new LoginForm(driver);
	    	if(login.login(username, pwd)) {
	    		AuthenticatedView autenticado = new AuthenticatedView(driver);
	    		PropertiesView view = autenticado.goToProps();
	    		if(view.existProp(propkey)) {
	    			view.deleteProp(propkey);
	    			view = autenticado.goToProps();
	    		}
	    		var newpropForm = view.addNewProp();
	    		newpropForm.saveNewProp(propkey, "descr", propgroup, propval);
	    		view = autenticado.goToProps();
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
	    			modalimport.importNewProp(filename);
	    			view = autenticado.goToProps();
	    			view.deleteByGroup(propgroup);
	    			assertTrue(true);
	    		} else {
	    			assertTrue(false,"problema en vista de propiedades");
	    		}
	    	} else {
	    		assertTrue(false,"problema en login?");
	    	}
		}
}
