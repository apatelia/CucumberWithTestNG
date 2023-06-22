package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
    WebDriver driver;

    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;

    public ProductDetailsPage(WebDriver driverFromTest) {
        this.driver = driverFromTest;

        PageFactory.initElements(this.driver, this);
    }

    public AddedToCartPage addProductToCart() {
        addToCartButton.click();

        return new AddedToCartPage(this.driver);
    }
}
