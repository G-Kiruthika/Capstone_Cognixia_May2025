package elements;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewTabHandler {
	
	WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.get("https://demoqa.com/browser-windows");
    }

    @Test(priority = 1)
    public void newTab() {
        WebElement newTabBtn = driver.findElement(By.xpath("//button[@id='tabButton']"));
        wait.until(ExpectedConditions.elementToBeClickable(newTabBtn));
        
        js.executeScript("arguments[0].scrollIntoView(true);", newTabBtn);
        js.executeScript("arguments[0].click();", newTabBtn);

        // Get window handles
        List<String> windows = new ArrayList<>(driver.getWindowHandles());

        // Switch to new tab
        driver.switchTo().window(windows.get(1));

        // Wait for and assert the text in the new tab
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading")));
        String headingText = heading.getText();
        System.out.println("Heading in new tab: " + headingText);
        Assert.assertEquals(headingText, "This is a sample page", "Heading text mismatch in new tab");

        driver.switchTo().window(windows.get(0));
    }
    

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
