Feature: Car Adding
  As a Developer
  I want to add Car trough API
  So that It can be available to applications

    Background:
      Given The Endpoint "http://localhost:%d/api/v1/cars/user" is available for cars

  @car-adding
  Scenario: Add Car
    When A Car Request is sent with values "Address", "Toyota", 20, "Focus", 9, 4, "si", 11, "ThePaint", 4, 45, "image", "Automatic", "Excellent"
    Then A Response with Status 200 is received for the car
    And A Car Resource with values "Address", "Toyota", 20, "Focus", 9, 4, "si", 11, "ThePaint", 4, 45, "image", "Automatic", "Excellent"