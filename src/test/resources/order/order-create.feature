Feature: Order Management

  As a customer
  I want to create an order with products and my details

  Scenario: Successfully create a new order

    Given the database has the following products
      | number                                  | name  | description | price |
      | 54ef44ff-0396-11f0-81ff-6d4319cda140    | Test  | asdsad      | 4     |

    When I send a POST request to "/orders" with the following body
      """
      {
        "products": [
          {
            "number": "54ef44ff-0396-11f0-81ff-6d4319cda140",
            "name": "Test",
            "description": "asdsad",
            "price": 4
          }
        ],
        "customer": {
          "name": "alexandre",
          "address": "bloco 92b"
        }
      }
      """

    Then the response status should be 201
    And the database should have the "orders" with the following data
      | customer.name | customer.address | products[0].number                   |
      | alexandre     | bloco 92b        | 54ef44ff-0396-11f0-81ff-6d4319cda140 |