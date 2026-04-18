package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Clicks {

    WebDriver driver;
    Actions actions;
    JavascriptExecutor js;
    
    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        driver.get("https://demoqa.com/buttons");
    }

    @Test(priority = 1)
    public void doubleClickTest() throws Exception {
        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        js.executeScript("arguments[0].scrollIntoView(true);", doubleClickBtn);
        actions.doubleClick(doubleClickBtn).perform();

        WebElement message = driver.findElement(By.xpath("//p[@id='doubleClickMessage']"));
        js.executeScript("arguments[0].scrollIntoView(true);", message);
        Thread.sleep(2000);
        Assert.assertTrue(message.isDisplayed(), "Double click message not displayed!");
        Assert.assertEquals(message.getText(), "You have done a double click", "Double click message mismatch!");
    }

    @Test(priority = 2)
    public void rightClickTest() throws Exception {
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        js.executeScript("arguments[0].scrollIntoView(true);", rightClickBtn);
        actions.contextClick(rightClickBtn).perform();

        WebElement message = driver.findElement(By.id("rightClickMessage"));
        js.executeScript("arguments[0].scrollIntoView(true);", message);
        Thread.sleep(2000);
        Assert.assertTrue(message.isDisplayed(), "Right click message not displayed!");
        Assert.assertEquals(message.getText(), "You have done a right click", "Right click message mismatch!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

