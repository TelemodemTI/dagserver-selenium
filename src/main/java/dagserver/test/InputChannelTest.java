package dagserver.test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import dagserver.pom.authenticated.AuthenticatedView;
import dagserver.pom.login.LoginForm;
import dagserver.utils.BaseTest;

public class InputChannelTest extends BaseTest  {

	@Test
    @Parameters({"url","username","pwd"})
	void gitHubChannelTest(String url,String username, String pwd) throws InterruptedException {
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
        			assertTrue(true);
        		}
    		}
    	}
	}
	
	

	@Test
    @Parameters({"url","username","pwd"})
	void rabbitChannelTest(String url,String username, String pwd) throws InterruptedException {
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
        			assertTrue(true);
        		}
    		}
    	}
	}
	
	@Test
    @Parameters({"url","username","pwd"})
	void redisChannelTest(String url,String username, String pwd) throws InterruptedException {
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
        			assertTrue(true);
        		}
    		}
    		
    	}
	}
}
