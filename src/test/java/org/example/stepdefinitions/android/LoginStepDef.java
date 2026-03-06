package org.example.stepdefinitions.android;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.driver.DriverManager;
import org.example.pages.android.LoginPage;
import org.example.pages.android.ProductsPage;

public class LoginStepDef {

    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    ProductsPage productsPage = new ProductsPage(DriverManager.getDriver());

    @Given("the user is on the General Store login screen")
    public void theUserIsOnTheGeneralStoreLoginScreen() {
        loginPage.verifyUserOnLoginScreen();
    }

    @When("the user selects {string} from the country dropdown")
    public void theUserSelectsFromTheCountryDropdown(String country) {
        loginPage.selectCountry(country);
    }

    @And("the user enters {string} in the name field")
    public void theUserEntersInTheNameField(String userName) {
        loginPage.enterUserName(userName);
    }

    @And("the user selects the {string} gender option")
    public void theUserSelectsTheGenderOption(String gender) {
       loginPage.selectGender(gender);
    }

    @And("the user clicks the lets shop button")
    public void theUserClicksTheLetsShopButton() {
        loginPage.clickOnLetsShop();
    }

    @Then("the user should be redirected to the product catalog screen")
    public void theUserShouldBeRedirectedToTheProductCatalogScreen() {
        productsPage.verifyUserNavToProductsPage();
    }

    @Then("a toast message or error should appear saying {string}")
    public void aToastMessageOrErrorShouldAppearSaying(String message) {
        loginPage.validateUserNameErrorToast(message);
    }

    @And("the user should remain on the login screen")
    public void theUserShouldRemainOnTheLoginScreen() {
        loginPage.verifyUserOnLoginScreen();
    }
}
