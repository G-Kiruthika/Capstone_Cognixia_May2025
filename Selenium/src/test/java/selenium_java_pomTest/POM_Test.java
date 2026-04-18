package selenium_java_pomTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import config.configReader;
import selenium_java_pomPages.Login;
import selenium_java_pomPages.Search;

import util.Log;
import util.ReadExcelSearchFunctionality;
import selenium_java_pomPages.AddToWishlist;
import selenium_java_pomPages.BaseTest;
import selenium_java_pomPages.Filter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import util.ExtentReportManager;

import util.AssertUtil;

public class POM_Test extends BaseTest{
	
	ExtentReports extent = ExtentReportManager.getInstance();
    ExtentTest test1;
    ExtentTest test2;
    ExtentTest test3;
	ExtentTest test4;
	ExtentTest test5;
	ExtentTest test6;
	
	//negative login testcase
	@Test(priority=1)
    public void loginInvalidEmail() throws Exception {
		Login loginPage = new Login(driver);
		SoftAssert softAssert = new SoftAssert();
		test2 = extent.createTest("testInvalidEmailLogin");
		
        Log.startTestCase("verifyInvalidEmailLogin");
        
      //verify SignIn page title
  	
      	String expectedTitle = loginPage.loginPageTitleCheck();
        String actualTitle = configReader.loginTitle;
        boolean loginPageTitle = expectedTitle.contains(actualTitle);

        test2.info("Page Title: " + expectedTitle);
        AssertUtil.assertTrueWithLogAndExtent( softAssert,
        		loginPageTitle,
            "LoginPageTitleValidation",
            log,
            test2
        );
      	//check the visibility of email
      	AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyEmailVisibility(), "verifyEmailVisibility", log, test2);
      		
      	//check if email field is enabled
      	AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyEmailClickability(), "verifyEmailClickability", log, test2);
      		
        loginPage.enterEmail(configReader.invalidEmail);
        loginPage.clickContinue();
        Thread.sleep(2000);
        String expectedError = "We couldn't find this eBay account.";
        String actualError = loginPage.emailError();
        boolean emailErrorMatches = actualError.equals(expectedError);
        AssertUtil.assertTrueWithLogAndExtent(softAssert, emailErrorMatches, "EmailErrorValidation", log, test1);
        Log.endTestCase("verifyInvalidEmailLogin");
        
    }
	
	@Test(priority=2)
    public void loginInvalidPassword() throws Exception {
		Login loginPage = new Login(driver);
		SoftAssert softAssert = new SoftAssert();
		test3 = extent.createTest("testInvalidPasswordLogin");
		
		//driver.get(configReader.get("url"));
        Log.startTestCase("verifyInvalidPasswordLogin");
      //verify SignIn page title

      	String expectedTitle = loginPage.loginPageTitleCheck();
        String actualTitle = configReader.loginTitle;
        boolean loginPageTitle = expectedTitle.contains(actualTitle);

        test3.info("Page Title: " + expectedTitle);
        AssertUtil.assertTrueWithLogAndExtent( softAssert,
        		loginPageTitle,
            "LoginPageTitleValidation",
            log,
            test3
        );
      	//check the visibility of email
      	AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyEmailVisibility(),"verifyEmailVisibility", log, test3);
      		
      	//check if email field is enabled
      	AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyEmailClickability(),"verifyEmailClickability", log, test3);
      		
        loginPage.enterEmail(configReader.validEmail);
        
      //check if continue button is visible
        AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyContinueBtnVisibility(), "verifyContinueBtnVisibility", log, test3);
        
        //check if continue button is enabled
        AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyContinueBtnClickability(), "verifyContinueBtnClickability", log, test3);
        
      //check the text in continue button
        AssertJUnit.assertTrue(loginPage.verifyContinueBtnText().contains(configReader.continueBtnText));
        boolean continueBtnTextMatch=loginPage.verifyContinueBtnText().contains(configReader.continueBtnText);
        AssertUtil.assertTrueWithLogAndExtent(softAssert, continueBtnTextMatch, "continueBtnTextMatch", log, test3);
        loginPage.clickContinue();
        
      //check the visibility of password
        AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyPasswordVisibility(),"verifyPasswordVisibility", log, test3);
        
      //check if password field is enabled
        AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyPasswordClickability(), "verifyPasswordClickability", log, test3);
		
        loginPage.enterPassword(configReader.invalidPassword);
        loginPage.clickSignIn();
        
        Thread.sleep(2000);
        
        String expectedError = "This password is incorrect. Try again or reset password.";
        String actualError = loginPage.passwordError();
        boolean passwordErrorMatches = actualError.equals(expectedError);
        AssertUtil.assertTrueWithLogAndExtent(softAssert, passwordErrorMatches, "passwordErrorValidation", log, test1);
        Log.endTestCase("verifyInvalidPasswordLogin");
        
    }
	//positive login testcase
	@Test(priority=3)
    public void loginToAccountPositive() throws Exception {
		Login loginPage = new Login(driver);
		SoftAssert softAssert = new SoftAssert();
		test1 = extent.createTest("testValidLogin");
		
		//driver.get(configReader.get("url"));
		//verify SignIn page title
		Log.startTestCase("verifyLogin");
		
      	String expectedTitle = loginPage.loginPageTitleCheck();
        String actualTitle = configReader.loginTitle;
        boolean loginPageTitle = expectedTitle.contains(actualTitle);

        test1.info("Page Title: " + expectedTitle);
        AssertUtil.assertTrueWithLogAndExtent( softAssert,
        		loginPageTitle,
            "LoginPageTitleValidation",
            log,
            test1
        );
		
		//check the visibility of email
		AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyEmailVisibility(), "verifyEmailVisibility", log, test1);
		
		//check if email field is enabled
		AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyEmailClickability(), "verifyEmailClickability", log, test1);
		
        loginPage.enterEmail(configReader.validEmail);
        
        //check if continue button is visible
        AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyContinueBtnVisibility(), "verifyContinueBtnVisibility", log, test1);
        
        //check if continue button is enabled
        AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyContinueBtnClickability(), "verifyContinueBtnClickability", log, test1);
        
      //check the text in continue button
        AssertJUnit.assertTrue(loginPage.verifyContinueBtnText().contains(configReader.continueBtnText));
        boolean continueBtnTextMatch=loginPage.verifyContinueBtnText().contains(configReader.continueBtnText);
        AssertUtil.assertTrueWithLogAndExtent(softAssert, continueBtnTextMatch, "continueBtnTextMatch", log, test1);
        loginPage.clickContinue();
        
      //check the visibility of password
        AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyPasswordVisibility(),"verifyPasswordVisibility", log, test1);
        
      //check if password field is enabled
        AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifyPasswordClickability(), "verifyPasswordClickability",log, test1);
		
        loginPage.enterPassword(configReader.validPassword);
        
        //check if signIn button is visible
        AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifySignInBtnVisibility(), "verifySignInBtnVisibility",log, test1);
        
        //check if signIn button is enabled
        AssertUtil.assertTrueWithLogAndExtent(softAssert, loginPage.verifySignInBtnClickability(), "verifySignInBtnClickability", log, test1);
        
        //check the text of signIn button
        boolean signinBtnTextMatch=loginPage.verifySignInBtnText().contains(configReader.signInBtnText);
        AssertUtil.assertTrueWithLogAndExtent(softAssert, signinBtnTextMatch, "signinBtnTextMatch", log, test1);        
        loginPage.clickSignIn();
        Thread.sleep(3000);
        
        String actualTitle1 = loginPage.homePageTitleCheck();
        
        String expectedTitle1 = "Electronics, Cars, Fashion, Collectibles & More | eBay";
        boolean homePageTitle = actualTitle1.equals(expectedTitle1);

        test1.info("Page Title: " + actualTitle);
        AssertUtil.assertTrueWithLogAndExtent( softAssert,
        		homePageTitle,
            "HomePageTitleValidation",
            log,
            test1
        );


         Log.endTestCase("verifyLogin");
       
    }
	
	
	@Test(priority=4)
	public void runSearchesFromExcel() throws Exception {
		 SoftAssert softAssert = new SoftAssert();
		 test4 = extent.createTest("Data-Driven Search Test");
//		 Login login = new Login(driver);
//		 login.loginToAccount("qetestascend@gmail.com", "Kiruthika2002");
		 driver.get("https://www.ebay.com");
	    int rowCount = ReadExcelSearchFunctionality.getRowCount();

	    for (int i = 1; i <= rowCount; i++) { 
	        String searchTerm = ReadExcelSearchFunctionality.getSearchTerm(i);
	        System.out.println(" Searching for: " + searchTerm);

	        Search search = new Search(driver);
	        search.performSearch(searchTerm);
	        //Thread.sleep(1000);
	        search.clickSearch();
	        Thread.sleep(3000);       

	        
	        String expectedTitle = search.searchPageTitleCheck();
	        
            boolean SearchTitleContainsKeyword = expectedTitle.toLowerCase().contains(searchTerm);

            test4.info("Page Title: " + expectedTitle);
            AssertUtil.assertTrueWithLogAndExtent( softAssert,
                SearchTitleContainsKeyword,
                "SearchDataTitleValidation",
                log,
                test4
            );
            
            driver.navigate().back();
	        
	        // Add your assertion here
	    }
	    
	}
	@Test(priority=5)
    public void addToWish() throws Exception {
    	 SoftAssert softAssert = new SoftAssert();
		 test5 = extent.createTest("Add To Wishlist Test");
		 
		 Login login = new Login(driver);
		 Search search = new Search(driver);
		 AddToWishlist add = new AddToWishlist(driver);
		 
		 login.loginToAccount("qetestascend@gmail.com", "Kiruthika2002");
		 
		 search.searchProduct("fossil women watch");
		 
		 add.selectProduct();
		 
		 Thread.sleep(4000);
		 
		// Step 1: Reset state - if already in wishlist, unwatch it
		 String initialText = add.readAddToWishlistBtnText();
		 
		 if (initialText.equalsIgnoreCase("Added to watchlist")) {
		     System.out.println("Resetting state: Clicking 'Unwatch' to remove from wishlist");
		     add.clickAddToWishListBtn(); // Click to unwatch
		     Thread.sleep(2000);
		 }

		 // Step 2: Add to wishlist
		 add.clickAddToWishListBtn();
		 System.out.println("Clicked Add to wishlist");
		 Thread.sleep(2000);

		 // Step 3: Hover over the button to reveal "Unwatch"
		// add.hoverOverWishlistButton();
//		 String afterHoverText = add.getWishlistButtonText();
//
//		 // Assert that hover changes the text to "Unwatch"
//		 boolean isHoverTextCorrect = afterHoverText.equalsIgnoreCase("Unwatch");
//		 AssertUtil.assertTrueWithLogAndExtent(softAssert, isHoverTextCorrect,
//		         "Verify 'Unwatch' appears after adding to wishlist", log, test5);
//
//		 // Step 4: Click again to unwatch for clean state
//		 add.clickAddToWishListBtn();
		 System.out.println("Removed product from wishlist");
		 Thread.sleep(2000);

//		 // Step 5: Confirm it’s now back to "Add to Watchlist"
//		 String finalText = add.getWishlistButtonText();
//		 boolean isFinalTextCorrect = finalText.equalsIgnoreCase("Add to Watchlist");
//		 AssertUtil.assertTrueWithLogAndExtent(softAssert, isFinalTextCorrect,
//		         "Verify button returns to 'Add to Watchlist'", log, test5);
		 
    }
	
	@Test(priority=6)
	public void filterFunction() throws Exception {
		Search search = new Search(driver);
		Filter filter = new Filter(driver);
		SoftAssert softAssert = new SoftAssert();
		
		driver.get("https://www.ebay.com");
		 test6 = extent.createTest("Filter Functionality Test");
		 
		 search.searchProduct("watch women");
		 
		 filter.selectSortOption();
		 
		 String actualText = filter.SortAssertText();
		 String expectedText = "Sort: Time: newly listed";
		 boolean isSortTextCorrect = actualText.equalsIgnoreCase(expectedText);
		 AssertUtil.assertTrueWithLogAndExtent(softAssert, isSortTextCorrect,
		         "Sorting is done correctly", log, test6);
		 
		 filter.selectConditionOption();
		 
		 String actualText1 = filter.ConditionAssertText();
		 String expectedText1 = "New with tags";
		 boolean isConditionTextCorrect = actualText1.equalsIgnoreCase(expectedText1);
		 AssertUtil.assertTrueWithLogAndExtent(softAssert, isSortTextCorrect,
		         "Condition is chosen correctly", log, test6);
		 
		 filter.chooseBrand();
		 String actualText2 = filter.brandAssertText();
		
		 String expectedText2 = "Michael Kors";
		 
		 boolean isBrandTextCorrect = actualText2.contains(expectedText2);
		 AssertUtil.assertTrueWithLogAndExtent(softAssert, isBrandTextCorrect,
		         "Brand is chosen correctly", log, test6);
		 
    }
		 

}
