Feature: Login
  Scenario: User can login with valid credentials
    Given payload has a valid username and password
    When I send a POST request to the login endpoint
    Then the HTTP status code should be 200 "OK"
    And the response should include an authentication token


  Scenario: User login with invalid credentials
    Given payload has an invalid username and password
    When I send a POST request to the login endpoint
    Then the HTTP status code should be 401 "OK"
    And the response should include an error message

  Scenario: User login with empty body
    Given payload has is an empty body
    When I send a POST request to the login endpoint
    Then the HTTP status code should be 400 "bad request"
    And the response should include an error message
