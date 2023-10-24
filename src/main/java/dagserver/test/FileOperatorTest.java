package dagserver.test;

import static org.testng.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dagserver.pom.authenticated.AuthenticatedView;
import dagserver.pom.authenticated.JobsView;
import dagserver.pom.login.LoginForm;
import dagserver.utils.OperatorTestInterface;

public class FileOperatorTest extends OperatorTestInterface {

	@Override
	@Test
    @Parameters({"url","username","pwd","jarname","dagname","group","cronexpr","step1"})
	public void createDagDesignWithStepTest(String url,String username,String pwd,String jarname,String dagname,String group,String cronexpr,String step1,ITestContext context) throws Exception {
		driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		JobsView jobs = autenticado.goToJobs();
    		jobs.selectDesigndTab();
    		var newform = jobs.newJobForm();
    		newform.setName(jarname);
    		newform.createCronDag(dagname, group, cronexpr);
    		newform.addStep(dagname,step1,"main.cl.dagserver.infra.adapters.operators.FileOperator");
    		var params = newform.selectStage(step1);
    		var mode = context.getCurrentXmlTest().getParameter("mode");
    		var filepath = context.getCurrentXmlTest().getParameter("filepath");
    		var delimiter = context.getCurrentXmlTest().getParameter("rowdelimitr");
    		params.sendParameter("mode", mode, "list");
    		params.sendParameter("filepath", filepath,"input");
    		params.sendParameter("rowDelimiter", delimiter,"input");
    		params.save();
    		this.writeEvidence(context,"createDagDesignWithStepTest","OK",By.xpath("/html/body"));
    		assertTrue(true);
    	} else {
			this.writeEvidence(context,"createDagDesignWithStepTest","ERROR",By.xpath("/html/body"));
			assertTrue(false);
		}
	}

	@Override
	public void editDagDesignWithStepTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportDagDesignWithStepTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDagDesignWithStepTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void importDagDesignWithStepTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void compilateDagWithStepTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeCompilatedDagWithStepTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewCompilatedDagWithStepTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scheduleCompilatedDagWithStepTest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unscheduleCompilatedDagWithStepTest() {
		// TODO Auto-generated method stub
		
	}

}
