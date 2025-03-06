Feature: Purchasing processes

  Scenario Template: Buying a sweater with a check payment with params
    Given I am signed in
    When I add "<quantity>" of "<size>" selected sweater to the cart
    And I confirm an address, select pickup and payment method
    Then I confirm the order and make a screenshot of confirmation

    Examples:
      | size | quantity |
      | M    | 5        |