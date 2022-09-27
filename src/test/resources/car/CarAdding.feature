Feature: Car Adding
  As a Developer
  I want to add Car trough API
  So that It can be available to applications

    Background:
      Given The Endpoint "http://localhost:4020/api/v1/cars" is available for cars

  @car-adding
  Scenario: Add Car
    When A Car Request is sent with values "1011 C Alfredo Salazar San Isidro", "Toyota", 2012, "Focus", 90000, 4, 11000, "The Paint on the car is new", 4, 45, "https://images.pexels.com/photos/1300402/pexels-photo-1300402.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Automatic", "Excellent"
    Then A Response with Status 200 is received for the car
    And A Car Resource with values "1011 C Alfredo Salazar San Isidro", "Toyota", 2012, "Focus", 90000, 4, 11000, "The Paint on the car is new", 4, 45, "https://images.pexels.com/photos/1300402/pexels-photo-1300402.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Automatic", "Excellent"