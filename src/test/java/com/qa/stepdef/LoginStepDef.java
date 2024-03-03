package com.qa.stepdef;

import com.aventstack.extentreports.ExtentTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import com.qa.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;


/***
 * This class is used to define the step defination and mapped with the feature file based on the test cases.
 * Some function are written in this class itself and some of them are called from login,base and product class.
 */

public class LoginStepDef {
    public ExtentTest logInfo = null;
    @When("^I enter username as \"([^\"]*)\"$")
    public void iEnterUsernameAs(String username) throws InterruptedException, ClassNotFoundException {
             new LoginPage().enterUserName(username);

    }

    @When("^I enter password as \"([^\"]*)\"$")
    public void iEnterPasswordAs(String password) {
        new LoginPage().enterPassword(password);

    }
    @When("^I login$")
    public void iLogin() {
        new LoginPage().pressLoginBtn();

    }
    @Then("login should fail with an error {string}")
    public void loginShouldFailWithAnError(String err) {
        Assert.assertEquals(new LoginPage().getErrTxt(), err);
    }
    @Then("I should see Products page with title {string}")
    public void iShouldSeeProductsPageWithTitle(String arg0) {
        Assert.assertEquals(new ProductsPage().getTitle(), arg0);
    }

    @And("Login and Verify that I should see the page with title {string}")
    public void loginAndVerifyThatIShouldSeeThePageWithTitle(String arg0) {
        iLogin();
        iShouldSeeProductsPageWithTitle(arg0);
    }

    @Then("I click on setting icon")
    public void iClickOnSettingIcon() {
        new LoginPage().presssettingbutton();

    }

    @Then("I logout")
    public void iLogout() {
        new LoginPage().pressLogoutBtn();

    }


    @After
    public void quit(Scenario scenario){
        if(scenario.isFailed()){
            byte[] screenshot =new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }


    @When("I am on Product page, performed swipe action to navigate to the element")
    public void iAmOnProductPagePerformedSwipeActionToNavigateToTheElement() {
        new LoginPage().swipeBystartAndEndElement();
    }
    @And("Tab on the element")
    public void tabOnTheElement() {
    }

    @Then("validate that result are showing on the cart.{string}")
    public void validateThatResultAreShowingOnTheCart(String arg0) {
        String tittle= new LoginPage().getresultValidate();
        System.out.println("Expected:="+tittle);
        Assert.assertEquals(new LoginPage().getresultValidate(), arg0);
    }
}
