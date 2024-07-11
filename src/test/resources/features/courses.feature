Feature: courses
  Scenario: Can retrieve a list of all courses
    Given the courses endpoint is available
    When I send a GET request to the courses endpoint
    Then the HTTP status code should be 200 "OK"
    And the response should include an array of all courses