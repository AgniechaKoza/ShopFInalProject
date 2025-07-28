package steps;

import drivers.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.*;
import pages.components.AddToCartPopupComponent;
import pages.components.HeaderComponent;

public class BuyingASweaterSteps {
    private final WebDriver driver = DriverManager.getDriver();
    HeaderComponent headerComponent;

    @Given("I am signed in")
    public void signingIn() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
        headerComponent = new HeaderComponent(driver);
        headerComponent.signInClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("danielray@03.tml.waw.pl", "Danielray");
        Assert.assertEquals("Daniel Ray", headerComponent.getLoggedUserName());
    }

    @When("I add {string} of {string} selected sweater to the cart")
    public void selectAndAddToCart(String quantity, String size) {
        headerComponent.clickClothesLink();
        ClothesPage clothesPage = new ClothesPage(driver);
        clothesPage.goToSweaterSite();
        SweaterPage sweaterPage = new SweaterPage(driver);
        sweaterPage.selectSize(size);
        sweaterPage.selectQuantityUsingButtons(quantity);
        Assert.assertEquals(size, sweaterPage.getDisplayedSize());
//        Assert.assertEquals(quantity, sweaterPage.getDisplayedQuantity()); To  sobie darujemy bo Presta miesza Asertujemy koszyk
        sweaterPage.addToCart();
        AddToCartPopupComponent addToCartPopupComponent = new AddToCartPopupComponent(driver);
        Assert.assertTrue("Adding to cart invalid message", addToCartPopupComponent.getAddToCartMessage().contains("Product successfully added to your shopping cart"));
        Assert.assertTrue("Size in cart is incorrect.", addToCartPopupComponent.getSizeMessage().contains(size));
        System.out.printf("Oczekiwana ilość: '%s', ilość z popupu: '%s'%n", quantity, addToCartPopupComponent.getQuantityMessage());
        Assert.assertTrue("Quantity in cart is incorrect", addToCartPopupComponent.getQuantityMessage().contains(quantity));
        Assert.assertEquals(addToCartPopupComponent.getSingleCartPrice(), (sweaterPage.singleRegularPrice() * 0.8), 0.01);
        addToCartPopupComponent.clickProceedToCheckoutBtn();
    }

    @And("I confirm an address, select pickup and payment method")
    public void confirmingPurchaseDetails() {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickProceedToCheckoutBtn();
        PersonalInformationPage personalInformationPage = new PersonalInformationPage(driver);
        personalInformationPage.clickAddressConfirmBtn();
//        System.out.println("Before checkbox");
//        personalInformationPage.clickCheckbox(); Ten checkbox jest wejściowo klknięty nie będę walczyć z Prestą
//        System.out.println("Checkbox clicked");
        personalInformationPage.clickConfirmDeliveryBtn();
        personalInformationPage.clickPayCheckCheckbox();
        personalInformationPage.clickThermsAgreeCheckbox();
    }

    @Then("I confirm the order and make a screenshot of confirmation")
    public void orderConfirmation() {
        PersonalInformationPage personalInformationPage = new PersonalInformationPage(driver);
        personalInformationPage.clickPlaceOrderBtn();
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        System.out.println(orderConfirmationPage.getOrderReference());
        orderConfirmationPage.takeScreenshotOfOrderConfirmation();
        headerComponent.goToAccountPage();
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.clickOrderHistoryLink();
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        orderHistoryPage.getPaymentStatusLabel();
        System.out.println(orderHistoryPage.getPaymentStatusLabel());
        Assert.assertTrue("Wrong payment status", orderHistoryPage.getPaymentStatusLabel().contains("Awaiting check payment"));
        System.out.printf("Total value in order confirmation: '%s', in order history price: '%s'%n",orderConfirmationPage.getTotalValue(), orderHistoryPage.getOrderHistoryPrice());
        Assert.assertEquals(orderConfirmationPage.getTotalValue(), orderHistoryPage.getOrderHistoryPrice(),0.01);
    }
}
