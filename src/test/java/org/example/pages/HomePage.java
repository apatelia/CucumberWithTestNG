package org.example.pages;

import org.example.utilities.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    WebDriver driver;

    @FindBy(id = "searchDropdownBox")
    WebElement searchCategoryDropDown;

    @FindBy(how = How.ID, using = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    public HomePage(WebDriver driverFromTest) {
        this.driver = driverFromTest;

        PageFactory.initElements(this.driver, this);
    }

    public void goTo() {
        String homePageUrl = ConfigReader.getBaseUrl();
        driver.get(homePageUrl);
    }

    public void selectCategoryToSearch(String category) {
        Select categoryList = new Select(searchCategoryDropDown);
        categoryList.selectByVisibleText(category);
    }

    public SearchResultsPage searchFor(String searchTerm) {
        searchBox.sendKeys(searchTerm);
        searchButton.click();

        return new SearchResultsPage(this.driver);
    }
}
