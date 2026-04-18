package util;
 
import org.apache.log4j.Logger;
 
public class Log {
 
    // Initialize Logger

    private static Logger Log = Logger.getLogger("Capstone");
 
    // Start of test case logging

    public static void startTestCase(String testCaseName) {

        Log.info("===================================================================================");

        Log.info("STARTING TEST CASE: " + testCaseName + " ");

        Log.info("===================================================================================");

    }
    
    // End of test case logging
    public static void endTestCase(String testCaseName) {

        Log.info("===================================================================================");

        Log.info(" END OF TEST CASE: " + testCaseName + " ");

        Log.info("===================================================================================");

    }
 
    // Info log

    public static void info(String message) {

        Log.info("info:  " + message);

    }
    // Warning log
    public static void warn(String message) {

        Log.warn("Warning:  " + message);

    }
    // Error log

    public static void error(String message) {

        Log.error("Error: " + message);
    }

}

 