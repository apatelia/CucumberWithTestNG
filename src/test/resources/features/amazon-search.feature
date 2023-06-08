Feature: Amazon Search
  Searching functionality on Amazon India website

  Scenario: User is able to search for a product in selected product category
    Given user is on Amazon India home page
    When user selects "Furniture" from search category list
    Then user searches for "table" using search text box
    Then page title must contain "table"
    Then user goes to the last search results page
    Then user clicks on the last product on the page
    Then user adds the product to the cart
    Then the product should be successfully added to the cart
    Then the user clicks on Go To Cart button
    Then the user removes the product from the cart
    Then the empty cart message should be displayed to the user