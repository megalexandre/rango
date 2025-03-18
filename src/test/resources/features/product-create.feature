Feature: Products

  Scenario: create a product

    Given I have no products
    When I send a product to "/products"
    """
    {
      "name": "create a product",
      "description": "any description",
      "price": 4
    }
    """

    Then I should see the keys with code 201
    """
    {
      "number": "any id"
    }
    """
    And database should have a product with the following data
      | name             | description     | price |
      | create a product | any description | 4     |

