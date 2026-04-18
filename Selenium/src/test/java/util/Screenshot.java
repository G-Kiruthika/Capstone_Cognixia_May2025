package util;
 
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
import java.io.File;
 
public class Screenshot {
 
    private static final Logger log = Logger.getLogger(Screenshot.class);
 
    public static void captureScreenshot(WebDriver driver, String fileName) {
        try {
            File screenshotDir = new File("./screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
 
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotDir, fileName + ".png");
            FileUtils.copyFile(src, dest);
            log.info("Screenshot saved: " + dest.getAbsolutePath());
        } catch (Exception e) {
            log.error("Failed to capture screenshot: " + e.getMessage(), e);
        }
    }
}