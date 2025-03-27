Feature: Booking for home cleaning services
  This feature file contains scenarios for booking of home cleaning services

@OneTime
  Scenario: User creates one time booking of home services
    Given I am on the JustLife home cleaning services booking page
    Then I select "2" hours of service by the profession
    And I select "1" professional for service
    And I choose "Yes" as material selection
    Then I click on next step
    Then I add "Dubai Marina" as Address if address window pops up
    Then I skip popular add-ons
    Then I choose the frequency as "One Time" 
    And I choose suggested professional number 1
    And I choose the "first available" date 
    And I choose "first available" time
    Then I click on next step
    And I enter phone number "501234567"
    And I enter OTP "4040"
    Then I check if login successful
    Then I select cash as payment method
    Then I complete the booking
    And check if order is placed
    
@Weekly
  Scenario: User creates weekly booking of home services
    Given I am on the JustLife home cleaning services booking page
    Then I select "2" hours of service by the profession
    And I select "2" professional for service
    And I choose "No" as material selection
    Then I click on next step
    Then I add "Dubai Marina" as Address if address window pops up
    Then I skip popular add-ons
    Then I choose the frequency as "Weekly" 
    And I choose the "Tomorrow" date 
    And I choose "9:00" time
    Then I click on next step
    And I enter phone number "501234567"
    And I enter OTP "4040"
    Then I check if login successful
    Then I select cash as payment method
    Then I complete the booking
    And check if order is placed