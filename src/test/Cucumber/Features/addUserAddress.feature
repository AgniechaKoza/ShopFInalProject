Feature: Adding user address after login

  Scenario Outline: Adding an address with params
    Given I'm on shop start page
    When I sign in
    And I entered address alias "<alias>" address "<address>" city "<city>" zip code "<zip code>" country "<country>" and phone "<phone>" in Addresses section
    Then I can see a success message with text "Address successfully added!"
    And I can see this address in the MyAddress window

    Examples:
      | alias | address         | city   | zip code | country        | phone     |
      | JaDo  | Funny Street 41 | Warsaw | 02-300   | United Kingdom | 333666777 |