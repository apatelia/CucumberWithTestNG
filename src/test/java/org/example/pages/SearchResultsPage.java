package org.example.pages;

import org.example.utilities.LogUtility;
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

public class SearchResultsPage {
    WebDriver driver;
    String searchResultsTabHandle;

    @FindBy(linkText = "2")
    WebElement secondPageLink;

    @FindBy(css = "span[class~='s-pagination-disabled']")
    WebElement lastPageLink;

    @FindBy(css = "div.s-result-item.s-asin.s-widget-spacing-small")
    List<WebElement> allProductsOnCurrentResultPage;

    public SearchResultsPage(WebDriver driverFromTest) {
        this.driver = driverFromTest;

        PageFactory.initElements(this.driver, this);
    }

    public void goToSecondLastResultsPage() {
        // First go to 2nd page
        secondPageLink.click();

        // Wait until the last page link is loaded/available.
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(lastPageLink));
        String lastPageNumber = lastPageLink.getText();

        // Calculate second last page number.
        int secondLastPageNumber = Integer.parseInt(lastPageNumber) - 1;

        // Get current URL.
        String url = driver.getCurrentUrl();
        // Replace page number with second last page number in current URL.
        String secondLastPageUrl = url.replace("&page=2&", "&page=" + secondLastPageNumber + "&");

        // Navigate to the updated URL.
        driver.get(secondLastPageUrl);
    }

    public ProductDetailsPage clickOnLastProductFromResults() {
        List<WebElement> productsWithoutAds = new ArrayList<>();

        for (WebElement product : allProductsOnCurrentResultPage) {
            if (!product.getAttribute("class").contains("AdHolder")) {
                // Add to cart button will be available only for products with price.
                // If the product has price, then only consider it for later use.
                if (!product.findElements(By.cssSelector("span.a-price-whole")).isEmpty())
                    productsWithoutAds.add(product);
            }
        }

        WebElement lastProduct = productsWithoutAds.get(productsWithoutAds.size() - 1);
        WebElement lastProductLink = lastProduct.findElement(By.cssSelector("h2 > a"));

        searchResultsTabHandle = driver.getWindowHandle();
        LogUtility.info("Clicking on product: " + lastProductLink.getText());
        LogUtility.info("Product url: " + lastProductLink.getAttribute("href"));
        lastProductLink.click();

        return new ProductDetailsPage(this.driver);
    }
}
