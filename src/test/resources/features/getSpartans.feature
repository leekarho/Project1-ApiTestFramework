Feature: get spartans
  Scenario: get all spartans with valid token
    Given I have a valid bearer token
    When I send a Get request to the Spartans endpoint
    Then the HTTP status code should be 200 "OK"
    And the response should include an array of all Spartans

  Scenario: get all spartans with invalid token
    Given I have an invalid bearer token
    When I send a Get request to the Spartans endpoint
    Then the HTTP status code should be 401 "Unauthorized"


