Feature: Product

  Scenario: get all products

    Given I have no products
    Given I have the following products
      | group  | name         | price | description                                                |
      | Promos | hot dog      | 5.00  | Lorem ipsum dolor sit amet, consectetur adipiscing elit.   |
      | Promos | Misto Quente | 15.00 | Suspendisse convallis mi nulla, ut varius eros iaculis eu. |

    When I fetch the product list "/products"
    Then I should see the available products
    """
    [
      {
        "group":"Promos",
        "name":"hot dog asd",
        "price_pretty":"R$ 5,00",
        "price":5.00,
        "description":"Lorem ipsum dolor sit amet, consectetur adipiscing elit."
      },
      {
        "group":"Promos",
        "name":"Misto Quente",
        "price_pretty": "R$ 15,00",
        "price": 15.00,
        "description": "Suspendisse convallis mi nulla, ut varius eros iaculis eu."
      }
    ]
    """

  Scenario: get all when we have no products

    Given I have no products

    When I fetch the product list "/products"
    Then I should see the available products
    """
    []
    """