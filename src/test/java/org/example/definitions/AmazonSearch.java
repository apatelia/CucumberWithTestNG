package org.example.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.*;
import org.example.utilities.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Set;

public class AmazonSearch {
    WebDriver driver = null;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ProductDetailsPage productDetailsPage;
    AddedToCartPage addedToCartPage;
    CartPage cartPage;

    @Before
    public void setUp() {
        driver = DriverManager.initializeDriver();

        homePage = new HomePage(driver);
    }

    @After
    public void teardown() {
        DriverManager.quitDriver();
    }

    @Given("user is on Amazon India home page")
    public void user_is_on_amazon_india_home_page() {
        homePage.goTo();
    }

    @When("user selects {string} from search category list")
    public void user_selects_from_search_category_list(String category) {
        homePage.selectCategoryToSearch(category);
    }

    @Then("user searches for {string} using search text box")
    public void user_searches_for_using_search_text_box(String searchTerm) {
        searchResultsPage = homePage.searchFor(searchTerm);
    }

    @Then("page title must contain {string}")
    public void page_title_must_contain(String searchTerm) {
        String title = driver.getTitle();

        Assert.assertTrue(title.contains(searchTerm));
    }

    @Then("user goes to the last search results page")
    public void user_goes_to_the_last_search_results_page() {
        searchResultsPage.goToLastResultsPage();
    }

    @Then("user clicks on the last product on the page")
    public void user_clicks_on_the_last_product_on_the_page() {
        productDetailsPage = searchResultsPage.clickOnLastProductFromResults();
    }

    @Then("user adds the product to the cart")
    public void user_adds_the_product_to_the_cart() {
        Set<String> allTabHandles = driver.getWindowHandles();
        String productPageTabHandle = allTabHandles.stream().toList().get(allTabHandles.size() - 1);
        driver.switchTo().window(productPageTabHandle);

        addedToCartPage = productDetailsPage.addProductToCart();
    }

    @Then("the product should be successfully added to the cart")
    public void the_product_should_be_successfully_added_to_the_cart() {
        addedToCartPage.verifyThatAddedToCartMessageIsDisplayed();
    }

    @Then("the user clicks on Go To Cart button")
    public void the_user_clicks_on_go_to_cart_button() {
        cartPage = addedToCartPage.goToCart();
    }

    @Then("the user removes the product from the cart")
    public void the_user_removes_the_product_from_the_cart() {
        cartPage.deleteProductFromTheCart();
    }

    @Then("the empty cart message should be displayed to the user")
    public void the_empty_cart_message_should_be_displayed_to_the_user() {
        cartPage.verifyThatCartIsEmpty();
    }
}
