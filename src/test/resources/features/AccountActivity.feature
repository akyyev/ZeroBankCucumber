    @accounts_activity
  Feature: Accounts Activity


    Background:
      Given user is on login page
      And user logs in with username "username" and password "password"
      When user clicks on "Account Activity" tab

    Scenario: Account Activity Page title
      And verify that page title is "Zero - Account Activity"

    Scenario: Verify Account drop down menu options
      And verify options are available on account dropdown
        | Savings |
        | Checking |
        | Savings  |
        | Loan     |
        | Credit Card |
        | Brokerage   |


    Scenario: Verify Transactions table column names are displayed
      And verify transactions table options are displayed
        | Date |
        | Description |
        | Deposit     |
        | Withdrawal |

