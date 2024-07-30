Feature: post spartans

  @ignore
  Scenario: create spartan with valid token
    Given I have a valid bearer token
    When I send a POST request to the relevant endpoint with a valid JSON body
    Then the HTTP status code should be 201 "Created"
    And the response should include the details of the newly created Spartan

  Scenario: create spartan with invalid token
    Given I have an invalid bearer token
    When I send a POST request to the relevant endpoint with a valid JSON body
    Then the HTTP status code should be 401 "Unauthorised"

  Scenario: create spartan with missing fields
    Given I have a valid bearer token
    When I send a POST request to the relevant endpoint with payload with missing fields
    Then the HTTP status code should be 400 "Bad Request"
    And the response should include error messages indicating the validation issues.
