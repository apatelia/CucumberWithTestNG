package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AddedToCartPage {
    WebDriver driver;

    @FindBy(css = "span.sw-atc-text")
    WebElement addedToCartMessage;

    @FindBy(linkText = "Go to Cart")
    WebElement goToCartButton;

    public AddedToCartPage(WebDriver driverFromTest) {
        this.driver = driverFromTest;

        PageFactory.initElements(this.driver, this);
    }

    public void verifyThatAddedToCartMessageIsDisplayed() {
        Assert.assertTrue(addedToCartMessage.isDisplayed());
        Assert.assertEquals(addedToCartMessage.getText(), "Added to Cart");
    }

    public CartPage goToCart() {
        goToCartButton.click();

        return new CartPage(this.driver);
    }
}
