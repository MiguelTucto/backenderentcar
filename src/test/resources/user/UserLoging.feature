Feature: User Logging
  As a User
  I want to logging User trough API
  So I can use the app

    Background:
      Given The Endpoint "http://localhost:%d/api/v1/users/login" is available for logging users

  @user-logging
  Scenario: Logging User
    When A User request is sent with values "Angel", "Rivera"
    Then A Response with Status 200 is received for logging user
    And A User Resource with values "Angel", "Rivera", "miguelito", "miguelito", "photo", 999