package dagserver.test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import dagserver.pom.authenticated.AuthenticatedView;
import dagserver.pom.authenticated.DependenciesView;
import dagserver.pom.authenticated.JobsDetailView;
import dagserver.pom.authenticated.JobsView;
import dagserver.pom.authenticated.LogsDetailView;
import dagserver.pom.login.LoginForm;
import dagserver.utils.BaseTest;

public class JobsTest extends BaseTest {

	@Test
    @Parameters({"url","username","pwd"})
	void systemJobsTest(String url,String username, String pwd) throws InterruptedException {
		driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		JobsView jobs = autenticado.goToJobs();
    		jobs.selectCompiledTab();
    		if(jobs.existJob("background_system_dag") && jobs.existJob("event_system_dag")) {
    			jobs.selectOption("background_system_dag", 1);
    			JobsDetailView detail = new JobsDetailView(driver);
    			detail.selectTab();
    			try {
    				detail.selectStage("background_system_dag", "internal");	
    				assertTrue(false,"existe un problema en el canvas de sistema?");
    			} catch (Exception e) {
    				jobs = autenticado.goToJobs();
    				jobs.selectCompiledTab();
    				jobs.selectOption("background_system_dag", 2);
    				LogsDetailView logdetail = new LogsDetailView(driver);
    				var data = logdetail.getActualLogs();
    				if(!data.isEmpty()) {
    					var logdata = data.get(0);
    					logdetail.deleteById(logdata.get("Id"));
    					if(!logdetail.existLog(logdata.get("Id"))) {
    						var data1 = logdetail.getActualLogs();
    						var logdata1 = data1.get(0);
    						logdetail.viewLog(logdata1.get("Id"));
    					} else {
    						assertTrue(false,"existe un problema en el despliegue de los logs");
    					}
    				}
    				jobs = autenticado.goToJobs();
    				jobs.selectCompiledTab();
    				jobs.selectOption("background_system_dag", 3);
    				new DependenciesView(driver);
    				assertTrue(true);
				}
    			
    		} else {
    			assertTrue(false,"Jobs de sistema no incluidos?");
    		}
    	}
	}
	
	@Test
    @Parameters({"url","username","pwd","jarname","dagname","group","cronexpr","step1","step2"})
	void addDesignCronTest(String url,String username, String pwd, String jarname,String dagname,String group,String cronexpr,String step1, String step2) throws InterruptedException {
		driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		JobsView jobs = autenticado.goToJobs();
    		var newform = jobs.newJobForm();
    		newform.setName(jarname);
    		newform.createCronDag(dagname, group, cronexpr);
    		newform.addDummyStep(step1);
    		var params = newform.selectStage(step1);
    		params.remove();
    		newform.addDummyStep(step2);
    		var params2 = newform.selectStage(step2);
    		params2.close();
    		newform.save();
    		assertTrue(true);
    	}
	}
	@Test
    @Parameters({"url","username","pwd","jarname","dagname","group","listenerType","triggerType","nameTarget","step1","step2"})
	void addDesignListenerTest(String url,String username, String pwd, String jarname,String dagname,String group,String listenerType,String triggerType,String nameTarget,String step1, String step2) throws InterruptedException {
		driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		JobsView jobs = autenticado.goToJobs();
    		var newform = jobs.newJobForm();
    		newform.setName(jarname);
    		newform.createListenerDag(dagname, group,listenerType,triggerType,nameTarget);
    		newform.addDummyStep(step1);
    		var params = newform.selectStage(step1);
    		params.remove();
    		newform.addDummyStep(step2);
    		var params2 = newform.selectStage(step2);
    		params2.close();
    		newform.save();
    		assertTrue(true);
    	}
	}
}
