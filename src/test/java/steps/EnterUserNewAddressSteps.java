package steps;

import drivers.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.*;
import pages.components.HeaderComponent;

public class EnterUserNewAddressSteps {
    private final WebDriver driver = DriverManager.getDriver();
    HeaderComponent headerComponent;
    MyAddressesPage myAddressesPage;

    String expectedAlias;
    String expectedAddress;
    String expectedCity;
    String expectedPostalCode;
    String expectedCountry;
    String expectedPhone;


    @Given("I'm on shop start page")
    public void userGoingToSignInSite() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
        headerComponent = new HeaderComponent(driver);
        headerComponent.signInClick();
    }

    @When("I sign in")
    public void signInUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("paulpull@03.tml.waw.pl", "Paulpull");
        Assert.assertEquals("Paul Pull", headerComponent.getLoggedUserName());
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.clickAddressBtn();
    }

    @And("I entered address alias {string} address {string} city {string} zip code {string} country {string} and phone {string} in Addresses section")
    public void EnteredNewAddress(String alias, String address, String city, String postal_code, String country, String phone) {
        this.expectedAlias = alias;
        this.expectedAddress = address;
        this.expectedCity = city;
        this.expectedPostalCode = postal_code;
        this.expectedCountry = country;
        this.expectedPhone = phone;
        NewAddressPage newAddressPage = new NewAddressPage(driver);
        newAddressPage.enterAddressData(alias, address, city, postal_code, country, phone);
    }

    @Then("I can see a success message with text {string}")
    public void checkSuccessMessage(String expectedMessage) {
        myAddressesPage = new MyAddressesPage(driver);
        System.out.println(myAddressesPage.successMessageText());
        Assert.assertEquals(expectedMessage, myAddressesPage.successMessageText());
    }

    @And("I can see this address in the MyAddress window")
    public void comparingAddressData() {
        String addressBlockText = myAddressesPage.displayedAddressBlockText();
        Assert.assertEquals(expectedAlias, myAddressesPage.displayedAlias());
        String[] actualLines = addressBlockText.trim().split("\n");
        Assert.assertEquals(expectedAddress, actualLines[1]);
        Assert.assertEquals(expectedCity, actualLines[2]);
        Assert.assertEquals(expectedPostalCode, actualLines[3]);
        Assert.assertEquals(expectedCountry, actualLines[4]);
        Assert.assertEquals(expectedPhone, actualLines[5]);
    }
}
