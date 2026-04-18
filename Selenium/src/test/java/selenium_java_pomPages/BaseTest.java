package selenium_java_pomPages;

import config.configReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	public WebDriver driver;
	public Logger log = Logger.getLogger("Capstone");
	 
	@BeforeMethod
    public void setup() {
        configReader.loadConfig();
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
 
        String browser = configReader.get("browser").toLowerCase();
        String headless = configReader.get("headless").toLowerCase();
 
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless.equals("true")) {
                    chromeOptions.addArguments("--headless=new");
                }
                driver = new ChromeDriver(chromeOptions);
                log.info("Chrome started in " + (headless.equals("true") ? "headless" : "headed") + " mode.");
                break;
 
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless.equals("true")) {
                    edgeOptions.addArguments("--headless=new");
                }
                driver = new EdgeDriver(edgeOptions);
                log.info("Edge started in " + (headless.equals("true") ? "headless" : "headed") + " mode.");
                break;
 
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
 
        driver.manage().window().maximize();
        log.info("Navigating to URL ");
        driver.get(configReader.get("url"));
    }
 
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("Browser closed.");
        }
    }
}
