@new_payee
Feature: Add new payee under pay bills

  Background:
    Given user is on login page
    Then user logs in with username "username" and password "password"
    Then user clicks on "Pay Bills" tab
    And the user accesses the Add New Payee tab

    @smoke
  Scenario: Add a new payee
    And creates new payee using following information
      | Payee name    | The Law Offices of Hyde, Price & Sharks |
      | Payee Address | 100 Same st, AnyTown, USA, 10001        |
      | Account       | Checking                                |
      | Payee details | XYZ account                             |
    Then message "The new payee The Law Offices of Hyde, Price & Sharks was successfully created." should be displayed