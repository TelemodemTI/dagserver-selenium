package dagserver.utils;

import org.testng.ITestContext;

public abstract class OperatorTestInterface extends BaseTest {
	public abstract void createDagDesignWithStepTest(String url,String username, String pwd, String jarname,String dagname, String group, String cronexpr, String step1,ITestContext context) throws Exception;
	public abstract void editDagDesignWithStepTest();
	public abstract void exportDagDesignWithStepTest();
	public abstract void deleteDagDesignWithStepTest();
	public abstract void importDagDesignWithStepTest();
	public abstract void compilateDagWithStepTest();
	public abstract void executeCompilatedDagWithStepTest();
	public abstract void viewCompilatedDagWithStepTest();
	public abstract void scheduleCompilatedDagWithStepTest();
	public abstract void unscheduleCompilatedDagWithStepTest();
	
}
