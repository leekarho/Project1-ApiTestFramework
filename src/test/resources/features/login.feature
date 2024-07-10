Feature: Login
  Scenario: User can login with valid credentials
    Given payload has a valid username and password
    When I send a POST request to the login endpoint
    Then the HTTP status code should be 200 OK,
    And the response should include an authentication token