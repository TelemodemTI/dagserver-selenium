package dagserver.test.operators;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dagserver.pom.authenticated.AuthenticatedView;
import dagserver.pom.authenticated.EditDesignView;
import dagserver.pom.authenticated.JobsView;
import dagserver.pom.login.LoginForm;
import dagserver.utils.BaseTest;

public class RabbitOperatorPublisherUseCaseTest extends BaseTest {
	
	@Test
    @Parameters({"url","username","pwd","jarname","dagname","group","cronexpr","step1","step2"})
	public void createDagDesignWithStepTest(String url,String username,String pwd,String jarname,String dagname,String group,String cronexpr,String step1,String step2,ITestContext context) throws Exception {
		driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		JobsView jobs = autenticado.goToJobs();
    		jobs.selectDesigndTab();
    		if(jobs.existDesign(jarname)) {
    			jobs.deleteDesign(jarname);
    		}
    		var newform = jobs.newJobForm();
    		newform.setName(jarname);
    		newform.createCronDag(dagname, group, cronexpr);
    		
    		newform.addStep(dagname,step1,"main.cl.dagserver.infra.adapters.operators.GroovyOperator");    		
    		var cmd = context.getCurrentXmlTest().getParameter("source");
    		var params = newform.selectStage(step1);
    		params.sendScript(cmd);
    		params.save();
    		jobs.selectDesigndTab();
    		EditDesignView editor2 = jobs.editDesign(jarname);
			editor2.selectDag(dagname);
			editor2.addStep(dagname,step2,"main.cl.dagserver.infra.adapters.operators.RabbitMQOperator");    		
    		params = newform.selectStage(step2);
    		var moder = context.getCurrentXmlTest().getParameter("mode");
    		var bodyr = context.getCurrentXmlTest().getParameter("body");
    		
    		var hostr = this.getInfrastructure(this.getClass().getCanonicalName(), "host");
    		var usernamer = this.getInfrastructure(this.getClass().getCanonicalName(), "username");
    		var passwordr = this.getInfrastructure(this.getClass().getCanonicalName(), "password");
    		var portr = this.getInfrastructure(this.getClass().getCanonicalName(), "port");
    		var exchange = this.getInfrastructure(this.getClass().getCanonicalName(), "exchange");
    		var routingKey = this.getInfrastructure(this.getClass().getCanonicalName(), "routingKey");
    		var queue = this.getInfrastructure(this.getClass().getCanonicalName(), "queue");
    		
    		
    		params.sendParameter("host", hostr, "input");
    		params.sendParameter("username", usernamer, "input");
    		params.sendParameter("password", passwordr, "input");
    		params.sendParameter("port", portr, "input");
    		params.sendParameter("exchange", exchange, "input");
    		params.sendParameter("routingKey", routingKey, "input");
    		params.sendParameter("queue", queue, "input");
    		params.sendParameter("mode", moder, "list");
    		params.sendParameter("xcom", step1,"list");
    		params.sendScript(bodyr);
    		params.save();
    		editor2.save();
    		this.writeEvidence(context,"createDagDesignWithStepTest","OK",By.xpath("/html/body"));
    		assertTrue(true);
    	} else {
			this.writeEvidence(context,"createDagDesignWithStepTest","ERROR",By.xpath("/html/body"));
			assertTrue(false);
		}
	}
	
	
	@Test
    @Parameters({"url","username","pwd","jarname","dagname","step1"})
	public void editDagDesignWithStepTest(String url,String username,String pwd,String jarname,String dagname, String step1,ITestContext context) throws InterruptedException, IOException {
		driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		JobsView jobs = autenticado.goToJobs();
    		jobs.selectDesigndTab();
    		if(jobs.existDesign(jarname)) {
    			EditDesignView editor = jobs.editDesign(jarname);
    			editor.selectDag(dagname);
    			var result = editor.execute();
				var contentPrc = result.getOutputXcom(step1);
				this.writeEvidence(context,"editDagDesignWithStepTest","OK",By.xpath("/html/body"));
		    	assertNotNull(contentPrc);
    		} else {
    			this.writeEvidence(context,"editDagDesignWithStepTest","ERROR",By.xpath("/html/body"));
				assertTrue(false,"problema en editor?");
    		}
    	} else {
			this.writeEvidence(context,"editDagDesignWithStepTest","ERROR",By.xpath("/html/body"));
			assertTrue(false);
		}
	}
	
}
