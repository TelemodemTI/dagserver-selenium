package dagserver.test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import dagserver.pom.authenticated.AuthenticatedView;
import dagserver.pom.login.LoginForm;
import dagserver.utils.BaseTest;

public class InputChannelTest extends BaseTest  {

	@Test
    @Parameters({"url","username","pwd"})
	void gitHubChannelTest(String url,String username, String pwd,ITestContext context) throws InterruptedException, IOException {
		driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		var channel = autenticado.goToInputChannels();
    		var dialog = channel.githubModal();
    		dialog.createNewWebhook("name","url","secret","SYSTEM","background_system_dag");
    		channel = autenticado.goToInputChannels();
    		dialog = channel.githubModal();
    		if(dialog.existRepo("url")) {
    			dialog.removeRepo("url");
    			channel = autenticado.goToInputChannels();
        		dialog = channel.githubModal();
        		if(!dialog.existRepo("url")) {
        			this.writeEvidence(context,"gitHubChannelTest","OK",By.xpath("/html/body"));
        			assertTrue(true);
        		} else {
        			this.writeEvidence(context,"gitHubChannelTest","ERROR",By.xpath("/html/body"));
        			assertTrue(false,"no agrego el repo?");
        		}
    		} else {
    			this.writeEvidence(context,"gitHubChannelTest","ERROR",By.xpath("/html/body"));
    			assertTrue(false,"no existe el repo?");
    		}
    	}
	}
	
	

	@Test
    @Parameters({"url","username","pwd"})
	void rabbitChannelTest(String url,String username, String pwd,ITestContext context) throws InterruptedException, IOException {
		driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		var channel = autenticado.goToInputChannels();
    		var modal = channel.rabbitModal();
    		modal.configureRabbit("host","11","user","pwd");
    		channel = autenticado.goToInputChannels();
    		modal = channel.rabbitModal();
    		modal.addQueue("queue","SYSTEM","background_system_dag");
    		channel = autenticado.goToInputChannels();
    		modal = channel.rabbitModal();
    		if(modal.existQueue("queue")) {
    			modal.removeQueue("queue");
    			channel = autenticado.goToInputChannels();
        		modal = channel.rabbitModal();
        		if(!modal.existQueue("queue")) {
        			this.writeEvidence(context,"rabbitChannelTest","ERROR",By.xpath("/html/body"));
        			assertTrue(true);
        		} else {
        			this.writeEvidence(context,"rabbitChannelTest","ERROR",By.xpath("/html/body"));
        			assertTrue(false,"no agrego la queue?");
        		}
    		} else {
    			this.writeEvidence(context,"rabbitChannelTest","ERROR",By.xpath("/html/body"));
    			assertTrue(false,"no existe la queue?");
    		}
    	}
	}
	
	@Test
    @Parameters({"url","username","pwd"})
	void redisChannelTest(String url,String username, String pwd,ITestContext context) throws InterruptedException, IOException {
		driver.get(url);
    	LoginForm login = new LoginForm(driver);
    	if(login.login(username, pwd)) {
    		AuthenticatedView autenticado = new AuthenticatedView(driver);
    		var channel = autenticado.goToInputChannels();
    		var modal = channel.redisModal();
    		modal.redisConfiguration("false","user","pwd");
    		channel = autenticado.goToInputChannels();
    		modal = channel.redisModal();
    		modal.addListener("channel","SYSTEM","background_system_dag");
    		channel = autenticado.goToInputChannels();
    		modal = channel.redisModal();
    		if(modal.existChannel("channel")) {
    			modal.removeChannel("channel");
    			channel = autenticado.goToInputChannels();
        		modal = channel.redisModal();
        		if(!modal.existChannel("channel")) {
        			this.writeEvidence(context,"redisChannelTest","OK",By.xpath("/html/body"));
        			assertTrue(true);
        		} else {
        			this.writeEvidence(context,"redisChannelTest","ERROR",By.xpath("/html/body"));
        			assertTrue(false,"no se agrego el channel?");
        		}
    		} else {
    			this.writeEvidence(context,"redisChannelTest","ERROR",By.xpath("/html/body"));
    			assertTrue(false,"no existe el channel?");
    		}
    		
    	}
	}
}
