Feature: Rent Adding

  Scenario Outline: As a User I want to add Rent in eRentCar.
    Given I want to add a Rent
    And I add a Rent with startDate <startDate>, finishDate <finishDate>, amount <amount> and rate <rate>
    Then I should be able to see my Rent

    Examples:
    | startDate | finishDate  | amount  | rate  |
    | "Hoy"     | "Manana"    | 25      | 1     |
