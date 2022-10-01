Feature: Lessor User registers first time with his personal information
  Scenario: Correct personal information
    Given The User is in Register Section
    And Complete his personal information
    When Click the button Register
    Then The System will show a message