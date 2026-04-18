package selenium_java_pomPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.Log;
import config.configReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Filter {
	
	WebDriver driver;
    WebDriverWait wait;
    
    public Filter(WebDriver driver) {
    	this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
	}

    
    @FindBy(xpath="//button[@aria-label='Sort selector. Best Match selected.']")
    WebElement sortButton;
    
    @FindBy(xpath="//span[@class='fake-menu-button srp-controls__control']/span//ul/li[3]")
    WebElement sortChosenOption;
    
    @FindBy(xpath="//div[@class='srp-controls__resize-display']//button")
    WebElement conditionButton;
    
    @FindBy(xpath="//div[@class='srp-controls__resize-display']//ul//li[2]")
    WebElement conditionOption;
    
    @FindBy(xpath="//div[text()='New with tags']")
    WebElement conditionText;
    
    @FindBy(xpath="//span[text()='Sort: Time: newly listed']")
    WebElement sortText;
    
    @FindBy(xpath="//li[@class='x-refine__main__list'][2]//ul//li[1]//a")
    WebElement brandOption;
    
    @FindBy(xpath="//ul[@class='carousel__list']//li[2]//div//a//div")
    WebElement brandText;
    
    public void selectSortOption() {
    	wait.until(ExpectedConditions.visibilityOf(sortButton));
    	sortButton.click();
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[2]//ul")));
    	wait.until(ExpectedConditions.visibilityOf(sortChosenOption));
    	sortChosenOption.click();
    	
    }
    
    public String SortAssertText() {
    	return sortText.getText();
    }
    
    public void selectConditionOption() {
    	wait.until(ExpectedConditions.visibilityOf(conditionButton));
    	conditionButton.click();
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='srp-controls__resize-display']//ul")));
    	wait.until(ExpectedConditions.visibilityOf(conditionOption));
    	conditionOption.click();
    }
    
    public String ConditionAssertText() {
    	return conditionText.getText();
    }
    
    public void chooseBrand() throws Exception {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].scrollIntoView(true);", brandOption);
    	Thread.sleep(2000);
    	brandOption.click();
    }
    
    public String brandAssertText() {
    	return brandText.getText();
    }
    
}
