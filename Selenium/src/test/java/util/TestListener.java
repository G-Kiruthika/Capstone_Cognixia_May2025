package util;
 
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class TestListener implements ITestListener {
 
    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((selenium_java_pomPages.BaseTest) currentClass).driver;
 
        String methodName = result.getName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
 
        Screenshot.captureScreenshot(driver, methodName + "_" + timestamp);
        System.out.println("Screenshot captured for FAILED test: " + methodName);
    }
}