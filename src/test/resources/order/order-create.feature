Feature: Order Management

  Scenario: trying create a new order with a product that does not exist

    Given I have no products
    When I send a POST request to "/orders" with the following body
      """
      {
        "items": [
            {
                "product": {
                    "number": "54ef44ff-0396-11f0-81ff-6d4319cda140",
                    "name": "This Product does not exists",
                    "description": "tasty item",
                    "price": 27.99
                },
                "quantity": 1
            }
        ],
        "customer": {
            "name": "alexandre",
            "address": "bl 92b"
        }
      }
      """

    Then the response status should be 422
    Then the response should contain
      | message            | code |
      | Product not found  | 422  |