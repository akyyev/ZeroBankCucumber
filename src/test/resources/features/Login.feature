    @login
  Feature: Login Functionality

    Scenario: User logs in with valid credentials
      Given user is on login page
      And user logs in with username "username" and password "password"
      Then "Account Summary" page should be displayed

      @negative_scenario
    Scenario: User logs in with blank username or password
      Given user is on login page
      And user logs in with username "" and password "password"
      Then "Login and/or password are wrong." error message needs to be displayed

      @negative_scenario
    Scenario: User logs in with invalid credentials
      Given user is on login page
      And user logs in with username "invalid" and password "invalid password"
      Then "Login and/or password are wrong." error message needs to be displayed


