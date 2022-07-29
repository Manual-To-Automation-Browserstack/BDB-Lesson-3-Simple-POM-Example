package com.bdb.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

// Import the page object so we can create an instance of it.
import com.bdb.pageobjects.Search;

public class AmazonBrowserStack {
    // Your credentials will be used to log into BrowserStack. We are using environment variables as we don't want to hardcode login information in the code.
    public static final String AUTOMATE_USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    public static final String AUTOMATE_ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");

    // Using the above credentials, create a URL for connection to BrowserStack.
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static DesiredCapabilities caps = new DesiredCapabilities();


    public static void main(String[] args) throws Exception {
        // call the method to set up the capabilities.
        getDesiredCapabilities();

        // Using the URL we created above,create the web driver and connect to BrowserStack.
        final WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        Search searchObject = new Search(driver);

        // Wrap the code in a try / catch block in order to catch errors if / when they occur.
        try {
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
            searchObject.isAddedToCart();

            // Close out the web driver at the end.
            driver.quit();
        } catch (Exception e) {
            // Output the exception to the console.
            System.out.println("There was an exception: " + e);
            // Close out the web driver since there was an error.
            driver.quit();
        }
    }

    /**
     *  Set up the capabilities to run on BrowserStack.
     */
    private static void getDesiredCapabilities() {
        caps.setCapability("os", "OS X");
        caps.setCapability("os_version", "Monterey");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "latest");
        caps.setCapability("name", "BDB BrowserStack Login Test"); // test name
        caps.setCapability("build", "BdB Lesson 2 - Exercise"); // CI/CD job or build name
    }
}