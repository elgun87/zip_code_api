Feature: Negative
  @wip
  Scenario: Negative scenario
    Given Accept type is JSON
    And path zipcode is 50000
    When I send a GET request to "post code"
    Then status code must be 404
    Then content type must be "application/json"