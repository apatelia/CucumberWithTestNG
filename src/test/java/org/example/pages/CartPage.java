package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CartPage {
    WebDriver driver;

    @FindBy(css = "input[value='Delete']")
    WebElement deleteProductFromCartLink;

    @FindBy(tagName = "h1")
    WebElement emptyCartMessage;

    public CartPage(WebDriver driverFromTest) {
        this.driver = driverFromTest;

        PageFactory.initElements(this.driver, this);
    }

    public void deleteProductFromTheCart() {
        deleteProductFromCartLink.click();
    }

    public void verifyThatCartIsEmpty() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElement(emptyCartMessage, "empty.")));

        Assert.assertTrue(emptyCartMessage.isDisplayed());
        Assert.assertEquals(emptyCartMessage.getText(), "Your Amazon Cart is empty.");
    }
}
