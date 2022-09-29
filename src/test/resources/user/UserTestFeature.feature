Feature: User Logging
  As a User
  I want to logging User trough API
  So I can use the app

  Background:
    Given The Endpoint "http://localhost:%d/api/v1/users/" is available for logging users

  Scenario: See user information
    When a user request is sent
    |id|name|lastname|email|password|imageUrl|phone|
    | 1 | Angel   |   Rivera     | miguelito    |    miguelito    |   photo     |  999   |
    Then A Response with Status 200 is received for user request
  Scenario: Update user information
    When an update user request is sent
      |name|lastname|email|password|imageUrl|phone|
      | Angel   |   Rivera     | miguelito    |    miguelito    |   photo     |  999   |
    Then A Response with Status 200 is received for user request

