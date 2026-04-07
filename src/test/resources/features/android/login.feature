Feature: Login functionality verification

  @SuccessLogin @Positive
  Scenario: User successfully enters all details and starts shopping
    Given the user is on the General Store login screen
    When user enters passcode on BHIM App
    When the user selects "Afghanistan" from the country dropdown
    And the user enters "John Doe" in the name field
    And the user selects the "Male" gender option
    And the user clicks the lets shop button
    Then the user should be redirected to the product catalog screen

  @EmptyUserName @Negative
  Scenario: User attempts to shop without entering a name
    Given the user is on the General Store login screen
    When the user clicks the lets shop button
    Then a toast message or error should appear saying "Please enter your name"
    And the user should remain on the login screen