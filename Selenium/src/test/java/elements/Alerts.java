package elements;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.Log;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Alerts{
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	@BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/alerts");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }
	 
	 @Test(priority=1)
	 public void SimpleAlert() throws InterruptedException {
		 WebElement alertBox = driver.findElement(By.xpath("//button[@id='alertButton']"));
		 js.executeScript("arguments[0].scrollIntoView(true);", alertBox);
	     wait.until(ExpectedConditions.elementToBeClickable(alertBox)).click();
	     wait.until(ExpectedConditions.alertIsPresent());

		 Alert simpleAlert = driver.switchTo().alert();
		 
		 String alertText = simpleAlert.getText();
		 Assert.assertEquals(alertText, "You clicked a button", "Simple alert text mismatch!");
		 Thread.sleep(1000);
	        simpleAlert.accept();
	 }
	 @Test(priority=2)
	 public void ConfirmationAlert() throws InterruptedException {
		 WebElement alertBox = driver.findElement(By.xpath("//button[@id='confirmButton']"));
		 js.executeScript("arguments[0].scrollIntoView(true);", alertBox);
	     wait.until(ExpectedConditions.elementToBeClickable(alertBox)).click();
	     wait.until(ExpectedConditions.alertIsPresent());
		
		 Alert confirmAlert = driver.switchTo().alert();
		 String confirmText = confirmAlert.getText();
	     Assert.assertEquals(confirmText, "Do you confirm action?", "Confirmation alert text mismatch!");
	        
	     Thread.sleep(1000);
	     confirmAlert.accept();
	     
	     WebElement confirmMsg = driver.findElement(By.xpath("//span[@id='confirmResult']"));
	     String msg = confirmMsg.getText();
	     Assert.assertEquals(msg, "You selected Ok", "Confirm result message text mismatch!");

	 }
	 
	 @Test(priority = 3)
	    public void verifyPromptAlert() throws InterruptedException {
	        WebElement prompt  = driver.findElement(By.id("promtButton"));
	        js.executeScript("arguments[0].scrollIntoView(true);", prompt);
		     wait.until(ExpectedConditions.elementToBeClickable(prompt)).click();
		     wait.until(ExpectedConditions.alertIsPresent());
	        Alert promptAlert = driver.switchTo().alert();
	        String promptText = promptAlert.getText();
	        Assert.assertEquals(promptText, "Please enter your name", "Prompt alert text mismatch!");
	        
	        String name = "Harry Potter";
	        promptAlert.sendKeys(name);
	        promptAlert.accept();
	        
	        WebElement promptMsg = driver.findElement(By.xpath("//span[@id='promptResult']"));
		     String resultText = promptMsg.getText();
		     Assert.assertTrue(resultText.contains("You entered"), "Result text doesn't contain expected phrase!");
		     Assert.assertTrue(resultText.contains(name), "Result text doesn't contain the entered name!");

	    }
	 
	 @AfterClass
	    public void tearDown() throws InterruptedException {
	        Thread.sleep(2000);
	        driver.quit();
	    }
	

}
