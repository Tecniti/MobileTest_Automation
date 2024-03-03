Feature: Login scenarios

Scenario: [Nagative-Failed] Login with invalid user name ands correct password
  When I enter username as "invalidUsername"
  And I enter password as "secret_sauce"
  And Login and Verify that I should see the page with title "PRODUCTS"

Scenario Outline: [Pass] login should fail with an validation message if username/password is wrong.
  When I enter username as "<username>"
  And I enter password as "<password>"
  And I login
  Then login should fail with an error "<err>"
  Examples:
  | username | password | err |
  | standard_user | invalidPassword | Username and password do not match any user in this service. |

Scenario Outline: [Pass] login with correct username and password And Verify the tittle of the next screen after login.
  When I enter username as "<username>"
  And I enter password as "<password>"
  And I login
  Then I should see Products page with title "<title>"
  Examples:
  | username | password | title |
  | standard_user | secret_sauce | PRODUCTS |

Scenario: Perform swipe action on thr product page and validate the result.
  When  I am on Product page, performed swipe action to navigate to the element
  And  Tab on the element
  Then  validate that result are showing on the cart."BACK TO PRODUCTS"

