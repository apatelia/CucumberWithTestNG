package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchResultsPage {
    WebDriver driver;
    Set<String> allTabHandles;
    String searchResultsTabHandle;
    String productPageTabHandle;

    @FindBy(linkText = "2")
    WebElement secondPageLink;

    @FindBy(css = "span[class~='s-pagination-disabled']")
    WebElement lastPageLink;

    @FindBy(css = "div.s-result-item.s-widget-spacing-small")
    List<WebElement> allProductsOnCurrentResultPage;

    public SearchResultsPage(WebDriver driverFromTest) {
        this.driver = driverFromTest;

        PageFactory.initElements(this.driver, this);
    }

    public void goToLastResultsPage() {
        // First go to 2nd page
        secondPageLink.click();

        // Wait until the last page link is loaded/available.
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(lastPageLink));
        String lastPageNumber = lastPageLink.getText();

        // Get current URL.
        String url = driver.getCurrentUrl();
        // Replace page number with last page number in current URL.
        String lastPageUrl = url.replace("&page=2&", "&page=" + lastPageNumber + "&");

        // Navigate to the updated URL.
        driver.get(lastPageUrl);
    }

    public ProductDetailsPage clickOnLastProductFromResults() {
        List<WebElement> productsWithoutAds = new ArrayList<>();

        for (WebElement product : allProductsOnCurrentResultPage) {
            if (!product.getAttribute("class").contains("AdHolder"))
                productsWithoutAds.add(product);
        }

        WebElement lastProduct = productsWithoutAds.get(productsWithoutAds.size() - 1);
        WebElement lastProductLink = lastProduct.findElement(By.cssSelector("h2 > a"));

        searchResultsTabHandle = driver.getWindowHandle();
        lastProductLink.click();

        return new ProductDetailsPage(this.driver);
    }
}
