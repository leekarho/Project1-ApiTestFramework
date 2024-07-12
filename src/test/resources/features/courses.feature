Feature: courses
  Scenario: Get a list of all courses
    Given the courses endpoint is available
    When I send a GET request to the courses endpoint
    Then HTTP status code should be 200 "OK"
    And the response should include an array of all courses

  Scenario: Get a specific course by id
    Given the course with id 1 exists
    When I send a GET request to the courses endpoint with id 1
    Then HTTP status code should be 200
    And the response should include the details of the course with ID 1.

  @ignore
  Scenario: Get course by non-existent id
    Given the course id 999 does not exist
    When I send a GET request to the courses endpoint with id 999
    Then HTTP status code should be 404
    And response should include an error message

  Scenario: Get course by invalid id
    Given the id provided is invalid
    When I send a GET request to the courses endpoint with "invalid id"
    Then HTTP status code should be 400
    And response should include an error message