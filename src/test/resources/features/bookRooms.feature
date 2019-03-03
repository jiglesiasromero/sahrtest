Feature: Booking a room with random data

  Background: Open the home page of Spotahome
    Given I open the home page
    Then I am on home page

  Scenario: As a user I want to book a room selecting a ramdon city and date in home page
    When I select a random city in home
    And I choose randoms date in and date out
    And I click on Explore button
    Then I am on room selection page
    When I choose a room
    Then I am on room summary page
    When I click on Book now! button
    Then I am on Personal details page

  @web
  Scenario: As a user I want to book a room selecting a ramdon city in home page and a random date in room summary page
    When I select a random city in home
    And I click on Explore button
    Then I am on room selection page
    When I choose a room
    Then I am on room summary page
    When I choose randoms date in and date out availables
    And I click on Book now! button
    Then I am on Personal details page