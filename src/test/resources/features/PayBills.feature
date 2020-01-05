    @pay_bills
  Feature: Pay Bills
    
    Background: 
      Given user is on login page
      And user logs in with username "username" and password "password"
      When user clicks on "Pay Bills" tab
    

    Scenario: Verify page title
      Then verify that page title is "Zero - Pay Bills"


    Scenario: Verify Successfully made Pay Operation message
      Then user selects "Wells Fargo" as payee type and "Credit Card" as account type
      Then user enters "29300"  as amount of money for payment and selects day as "10-10-2019"
      And verify pay operation message "The payment was successfully submitted." is displayed


    @negative_scenario
    Scenario: Verify pop-up window notification when we left blank date or amount
      Then user selects "Bank of America" as payee type and "Brokerage" as account type
      Then user enters ""  as amount of money for payment and selects day as "2012-09-01"
      And verify pay operation error message "Please fill out this field." is displayed