package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WindowHandler {

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

    
    @Test
    public void newWindow()
    {
    	WebElement newWindowBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("windowButton")));
        js.executeScript("arguments[0].scrollIntoView(true);", newWindowBtn);
        newWindowBtn.click();

        // Store all window handles
        List<String> windows = new ArrayList<>(driver.getWindowHandles());

        // Switch to the newly opened window
        driver.switchTo().window(windows.get(1));

        // Wait for heading to be visible and assert its text
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading")));
        String actualText = heading.getText();
        System.out.println("Text in new window: " + actualText);
        Assert.assertEquals(actualText, "This is a sample page", "Text mismatch in new window");
        
        driver.close();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
