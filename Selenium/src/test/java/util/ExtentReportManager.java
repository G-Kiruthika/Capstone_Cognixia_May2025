package util;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class ExtentReportManager {
    private static ExtentReports extent;
 
    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/extent-report.html");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Capstone Automation Report");
            spark.config().setReportName("Test Execution Summary");
 
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}