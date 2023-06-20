Feature: Regist Users
  In order to have registered all my users then I should call an API to register them in database

  Scenario: Regist success of users
    Given alejandro is a client that wants to allow to register his users
    When send the related data for the register
    Then have an account registered successfully


  Scenario: Regist success of users with pojo
    Given alejandro is a client that wants to allow to register his users using pojo
    When send the related data for the register using pojo
    Then have an account registered successfully using pojo

  Scenario: Update success of users
    Given alejandro is a client that wants to allow to update his users
    When send the related data for the update
    Then have an account updated successfully

  Scenario: List success of users
    Given alejandro is a client that wants to allow to lists his users
    When send the related data for the list
    Then have displayed the list successfully

  Scenario: Delete success of users
    Given alejandro is a client that wants to allow to Delete his users
    When send the related data for the delete
    Then have an account deleted successfully