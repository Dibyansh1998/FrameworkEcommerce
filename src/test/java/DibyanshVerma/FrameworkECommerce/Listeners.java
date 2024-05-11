package DibyanshVerma.FrameworkECommerce;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReportNG;

public class Listeners extends Standalonetest implements ITestListener {
	ExtentTest test;

	ExtentReports extent = ExtentReportNG.getReportObject();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Passed");
		test.fail(result.getThrowable());// This will give the Error Message like what is failed on Test

		String filepath = null;
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block		
			e.printStackTrace();
		}
		
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());

		// Take a screenshot and Attaching into the report

	}

	

	@Override
	public void onFinish(ITestContext context) {
    	    	extent.flush();
	}

}
