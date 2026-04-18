package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class iFrame {
    
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoqa.com/frames");
    }

    @Test(priority = 1)
    public void handleFrameOne() {
        WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='frame1']"));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame1));

        // Get heading inside frame
        WebElement frameHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@id='sampleHeading']")));
        String headingText = frameHeading.getText();
        System.out.println("Frame 1 Text: " + headingText);

        // Assertion
        Assert.assertEquals(headingText, "This is a sample page", "Text mismatch in Frame 1");

        // Switch back to main content
        driver.switchTo().defaultContent();
    }

    @Test(priority = 2)
    public void handleFrameTwo() {
        WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='frame2']"));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame1));

        // Get heading inside frame
        WebElement frameHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@id='sampleHeading']")));
        String headingText = frameHeading.getText();
        System.out.println("Frame 2 Text: " + headingText);

        // Assertion
        Assert.assertEquals(headingText, "This is a sample page", "Text mismatch in Frame 2");

        // Switch back to main content
        driver.switchTo().defaultContent();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
