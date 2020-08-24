Feature: Ixigo Flight Search API

  Scenario: HTTP - Verify top 20 cheapest flight results for a round trip
    Given user will travel from "DEL" to destination "BOM" for "10" days
    When user serach on ixigo flight booking site
    Then user is able found top 20 cheapest flights