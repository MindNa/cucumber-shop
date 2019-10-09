Feature: check number of products
    As a customer
    I want to check number of products

Background:
    Given products Bread with price 20.50 with quantity 5 in stock
    And products Jam with price 80.00 with quantity 10 in stock

Scenario: I want to check quantity of product
    When I want to check quantity of Bread
    Then quantity of product Bread should be 5

Scenario: Buy multiple products
    When I buy Bread with quantity 2
    And I buy Jam with quantity 1
    Then total should be 121.00

Scenario: Buy products that are out of stock
    When I buy Bread with quantity 10
    Then a product Bread with quantity 10 should out of stock