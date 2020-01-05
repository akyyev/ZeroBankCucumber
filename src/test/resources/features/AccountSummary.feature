@account_summary
Feature: Account Summary Display

  Background: Logging in to web application
    Given user is on login page
    And user logs in with username "username" and password "password"

  Scenario: Account Summary Page Title
    Then Account Summary page should have title as "Zero - Account Summary"


  Scenario: Account types
    And verifies that account types are displayed
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |

  Scenario: Credits Accounts table
    And verifies that column names are displayed on Credits Accounts table
      | Account     |
      | Credit Card |
      | Balance     |

