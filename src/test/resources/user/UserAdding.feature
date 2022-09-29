Feature: User Adding
  As a Developer
  I want to add User trough API
  So that It can be available to applications

    Background:
      Given The Endpoint "http://localhost:%d/api/v1/users" is available for users

  @user-adding
  Scenario: Add User
    When A User Request is sent with values "Angel", "Rivera", "miguelito", "miguelito", "photo", 999
    Then A Response with Status 200 is received for the user
    And A User Resource is sent with values "Angel", "Rivera", "miguelito", "miguelito", "photo", 999