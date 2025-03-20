Feature: Order Management

  As a customer
  I want to create an order with products and my details

  Scenario: Successfully create a new order

    Given the database has the following products
      | number                               | name      | description | price |
      | 54ef44ff-0396-11f0-81ff-6d4319cda140 | Hamburger | tasty item  | 29.95 |
      | 273e21ff-040b-11f0-9ead-83eaa864a7ce | Coca      | tasty item  | 4     |

    When I send a POST request to "/orders" with the following body
      """
      {
        "items": [
            {
                "product": {
                    "number": "54ef44ff-0396-11f0-81ff-6d4319cda140",
                    "name": "Hamburger",
                    "description": "tasty item",
                    "price": 27.99
                },
                "quantity": 1
            },
            {
                "product": {
                    "number": "273e21ff-040b-11f0-9ead-83eaa864a7ce",
                    "name": "Coca",
                    "description": "tasty item",
                    "price": 2.00
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

    Then the response status should be 201
    Then the database should have the "orders" with the following data
      | number                               | customer.name | customer.address | item[0].product.number               | item[0].quantity | createdAt           | status |
      | 123e4567-e89b-12d3-a456-426614174000 | alexandre     | bl 92b           | 54ef44ff-0396-11f0-81ff-6d4319cda140 | 2                | 2025-03-19T12:00:00 | OPEN   |