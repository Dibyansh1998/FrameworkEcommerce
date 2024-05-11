package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject()
	{
		// ExtentReports, ExtentSparkReporter
				String path = System.getProperty("user.dir") + "\\reports\\index.html";
				ExtentSparkReporter report = new ExtentSparkReporter(path);
				report.config().setReportName("Web Automation Results");
				report.config().setDocumentTitle("Test Results");

				ExtentReports rep = new ExtentReports();
				rep.attachReporter(report);
				rep.setSystemInfo("Automation Tester", "Dibyansh Verma");
				return rep;
	}

}
