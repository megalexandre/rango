Feature: Product

  Scenario: get all products

    Given I have no products
    When I send the product "/products"
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
      "number": "create a product"
    }
    """

