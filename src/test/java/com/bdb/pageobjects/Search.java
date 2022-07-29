package com.bdb.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Search {
    public WebDriver driver;
    public WebDriverWait wait;

    /**
     *  Series of locators that will be used in this objects various methods.
     */
    By acceptCookies = By.cssSelector("#sp-cc-accept");
    By searchBox = By.cssSelector("#twotabsearchtextbox");
    By searchButton = By.cssSelector("#nav-search-submit-button");
    By firstItem = By.cssSelector("#search > div.s-desktop-width-max.s-desktop-content.s-opposite-dir.sg-row > div.s-matching-dir.sg-col-16-of-20.sg-col.sg-col-8-of-12.sg-col-12-of-16 > div > span:nth-child(4) > div.s-main-slot.s-result-list.s-search-results.sg-row > div:nth-child(3) > div > div > div > div > div > div > div > div.sg-col.sg-col-4-of-12.sg-col-4-of-16.sg-col-4-of-20.s-list-col-left > div > div.s-product-image-container.aok-relative.s-image-overlay-grey.s-text-center.s-padding-left-small.s-padding-right-small.s-flex-expand-height > div > span > a > div > img");
    By addToCart = By.cssSelector("#add-to-cart-button");
    By noExtraCoverage = By.cssSelector("#attachSiNoCoverage > span > input");
    By addedToBasket = By.cssSelector("#attach-added-to-cart-message");

    /**
     * Constructor for the Search object.
     * @param driver - The webdriver that will be used to perform the actions on the webpages.
     */
    public Search(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Common method to click the Accept Cookies button. Wrapped in a try / catch for rare occasions where this does not appear.
     */
    public void acceptCookies() {
        try {
            driver.findElement(acceptCookies).click();
        } catch (Exception e) {
            System.out.println("Cookie box did not appear");
        }
    }

    /**
     *  Common method to enter the search text and click on the search button.
     * @param searchTerm - The product / item that you want to search for
     */
    public void performSearch(String searchTerm) {
        driver.findElement(searchBox).sendKeys(searchTerm);
        driver.findElement(searchButton).click();
    }

    /**
     *  Common method to find the first item in the returned search and click on it.
     */
    public void getFirstItem() {
        driver.findElement(firstItem).click();
    }

    /**
     *  Common method to add an item to the cart
     */
    public void addToCart() {
        driver.findElement(addToCart).click();
    }

    /**
     *  Common method to decline the additional coverage that pops up when adding to cart.
     */
    public void declineExtraCoverage() {
        driver.findElement(noExtraCoverage).click();
    }

    /**
     *  Common method to verify that an item was added to the basket successfully.
     */
    public void isAddedToCart() {
        assert wait.until(ExpectedConditions.visibilityOfElementLocated(addedToBasket)).isDisplayed();
    }
}
