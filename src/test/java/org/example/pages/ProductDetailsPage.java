package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
    WebDriver driver;
    String productNameText;

    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;

    @FindBy(id = "buy-now-button")
    WebElement buyNowButton;

    @FindBy(id = "productTitle")
    WebElement productName;

    public ProductDetailsPage(WebDriver driverFromTest) {
        this.driver = driverFromTest;

        PageFactory.initElements(this.driver, this);
    }

    public String getProductNameText() {
        if (productNameText == null)
            productNameText = productName.getText();

        return productNameText;
    }

    public AddedToCartPage addProductToCart() {
        productNameText = productName.getText();

        addToCartButton.click();

        return new AddedToCartPage(this.driver);
    }
}
