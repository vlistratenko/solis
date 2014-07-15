package selenium;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;
import org.testng.IAnnotationTransformer;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.ITestAnnotation;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import selenium.SeleneseTestCase;


public class MyTestListener extends SeleneseTestCase implements ITestListener,IInvokedMethodListener, IAnnotationTransformer{
	Properties props = new Properties();
	static String propFileName = "properties.prop";
	boolean updateTC = Boolean.valueOf(CommonUtils.getProperty("updateTC", "false"));
	String planName = CommonUtils.getProperty("planName", "false");
    String buildName = CommonUtils.getProperty("buildVersion", "false");
	//Run when class ends
	@Override
	public void onFinish(ITestContext arg0) {
		
	}
	//Run when class starts
	@Override
	public void onStart(ITestContext arg0) {
		SeleneseTestCase.logger.debug("Start " + arg0.getName() + " class");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		SeleneseTestCase.logger.debug("Test " + result.getName() + " is succes but within Success %");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		File f = makeScreenshot(result.getName());
		String steps = "";
		for (int i = 0; i < bug.size(); i++) {
			steps =  steps + " \n   " + (i+1) + ". " + bug.get(i);
		}
		if (updateTC && result.getMethod().getDescription()!= null) {
			String[] ids = CommonUtils.getArrayFromStringBySymbol(result.getMethod().getDescription(), ":");
			Reporter.log("Execution failed. " + ids[2]);
			try {
				TestLink api = new TestLink(planName, buildName); 
				Integer internalID = new Integer(ids[0]), 
						externalID = new Integer(ids[1]);
				api.updateTestCaseResult(internalID, externalID, ExecutionStatus.FAILED, api.getLastExecutionId(externalID, internalID) + " - Execution failed. " + ids[2] + " \n Steps:" + steps);
				api.uploadExecutionAttachment(f);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		SeleneseTestCase.logger.error("Test " + result.getName() + " is FAILED");
		
		Reporter.log("Steps:");
		for (int i = 0; i < bug.size(); i++) {
			Reporter.log(bug.get(i));
		}
		Reporter.log("<a href='file:///" + f.getAbsolutePath() + "'>Screenshot</a>");
		/*if (createIssues) {
			String issueKey = new JiraAPI2().createIssue(result.getName(), "Core", bug, f).getKey();
			CommonUtils.saveIssueKey(result.getName(), issueKey);
			SeleneseTestCase.logger.info("New Issue was added. Issue �3" + issueKey);	
		}*/
		
				
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		SeleneseTestCase.logger.debug("Test " + result.getName() + " is SKIPPED");
		if (updateTC && result.getMethod().getDescription()!= null) {
			try {
				String[] ids = CommonUtils.getArrayFromStringBySymbol(result.getMethod().getDescription(), ":");
				TestLink api = new TestLink(planName, buildName);
				Integer internalID = new Integer(ids[0]), 
						externalID = new Integer(ids[1]);
						
				
				if (api.getLastExecutionResult(externalID, internalID).equals(ExecutionStatus.FAILED) && api.getLastExecutionNotes(externalID, internalID).contains(api.getLastExecutionId(externalID, internalID).toString())) {
					return;
				}else{
					 api.updateTestCaseResult(internalID, externalID, api.getLastExecutionResult(externalID, internalID), api.getLastExecutionId(externalID, internalID) + " - Was not run");
				}
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}

	@Override
	public void onTestStart(ITestResult test) {
		SeleneseTestCase.logger.info("Run " + test.getName() + " test");
		bug.clear();
/*		CommonUtils.SetParam("testResult", "true");
		String issueKey = CommonUtils.getIssueKey(test.getName());
		if (!issueKey.equals("")) {
			Issue issue = new JiraAPI2().getIssueByKey(issueKey);
			if (issue.getStatus().getName().equalsIgnoreCase("Готов к прогону")){
				 new JiraAPI2().setTransition(issueKey, "В работе"); 
			}else{
				throw new SkipException("Test has issues. Issue number - " + issueKey + ". Issue status - " + issue.getStatus().getName());
			}			
		}*/
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		SeleneseTestCase.logger.info("Test " + result.getName() + " is SUCCESS");
		
		if (updateTC && result.getMethod().getDescription()!= null) {
			String[] ids = CommonUtils.getArrayFromStringBySymbol(result.getMethod().getDescription(), ":");
			Reporter.log(ids[2].replace(" NOT", ""));
			Integer internalID = new Integer(ids[0]), 
					externalID = new Integer(ids[1]);
			try {
				new TestLink(planName, buildName).
				updateTestCaseResult(internalID, externalID, ExecutionStatus.PASSED, ids[2].replace(" NOT", ""));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
/*		System.err.println(method.getTestMethod().getMethodName() + " - " + result.isSuccess());
		if (!result.isSuccess()) {
		    String imageName = method.getTestMethod().getMethodName() + ".png";
		    String imagePath =new File("test-output\\screens\\" + imageName).getAbsolutePath() ;
		    try {
		      String base64Screenshot = selenium.captureEntirePageScreenshotToString("");
		      byte[] decodedScreenshot = Base64.decodeBase64(base64Screenshot.getBytes());
		      FileOutputStream fos = new FileOutputStream(new File(imagePath));
		      fos.write(decodedScreenshot);
		      fos.close();
		      Reporter.log("<a href=\"file:///" + imagePath + "\">Screenshot</a>");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }*/
		}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		//arg0.getTestMethod().setSkipFailedInvocations(true);
		
	}
	@Override
	public void transform(ITestAnnotation arg0, @SuppressWarnings("rawtypes") Class arg1, @SuppressWarnings("rawtypes") Constructor arg2,
			Method arg3) {
		// TODO Auto-generated method stub
		
	}

 

}
