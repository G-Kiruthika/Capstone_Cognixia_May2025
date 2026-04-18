package util;
 
import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;
import config.configReader; 

public class AssertUtil {
 
    public static void assertTrueWithLogAndExtent(SoftAssert softAssert,
                                                  boolean condition,
                                                  String checkMessage,
                                                  Logger log,
                                                  ExtentTest extentTest) {
 
    	String methodName = "UnknownMethod";
    	 
    	for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
    	    if (element.getClassName().contains("Test")) { // Adjust class name as needed
    	        methodName = element.getMethodName();
    	        break;
    	    }
    	}
    	
        if (!condition) {
            log.warn("[" + methodName + "] " + checkMessage + " " + configReader.assertFailMessage);
            if(extentTest != null) {
                extentTest.fail("WARNING: " + "[" + methodName + "] " + checkMessage + " " + configReader.assertFailMessage);
            }
        }
        
        else {
            log.info("[" + methodName + "] " + checkMessage + " " + configReader.assertPassMessage);
            if (extentTest != null) {
                extentTest.pass("[" + methodName + "] " + checkMessage + " " + configReader.assertPassMessage);
            }
        }
 
        softAssert.assertTrue(condition, configReader.assertFailMessage);
    }
}
 
 