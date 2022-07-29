package com.bdb.tests;
import com.bdb.pageobjects.Search;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AmazonLocal {
    public static void main(String[] args) throws Exception {
        // Setting the driver executable path
        System.setProperty("webdriver.chrome.driver", "/Users/garybehan/webdrivers/chromedriver");

        // Initiate the web driver as a Chrome instance.
        final WebDriver driver=new ChromeDriver();
        Search searchObject = new Search(driver);

        // Set the maximum wait time for timeouts
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Maximise the browser window
        driver.manage().window().maximize();

        //Open the browser with the URL that you want to interact with
        driver.get("https://www.amazon.co.uk");

        // Accept the cookies so you can interact with the web page
        searchObject.acceptCookies();

        // Perform a search by passing in a search term
        searchObject.performSearch( "iPhone 13");

        // Click on the first item returned in the search
        searchObject.getFirstItem();

        // Add the item to the cart
        searchObject.addToCart();

        // Decline the extra coverage that pops up
        searchObject.declineExtraCoverage();

        // Verify that the item added to the cart successfully.
        assert searchObject.isAddedToCart();

        // Close out the web driver at the end.
        driver.quit();
    }
}
