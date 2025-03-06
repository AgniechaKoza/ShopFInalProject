package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class PersonalInformationPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    @FindBy(css = "[name='confirm-addresses']")
    WebElement addressConfirmBtn;
    @FindBy(css = "[type='radio']#delivery_option_8")
    WebElement checkbox;
    @FindBy(css = "[name='confirmDeliveryOption']")
    WebElement confirmDeliveryBtn;
    @FindBy(css = "[for='payment-option-1']")
    WebElement payCheckCheckbox;
    @FindBy(className = "condition-label")
    WebElement thermsAgreeCheckbox;
    @FindBy(css = ".ps-shown-by-js .center-block")
    WebElement placeOrderBtn;

    public PersonalInformationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickAddressConfirmBtn() {
        wait.waitForClickable(addressConfirmBtn);
        addressConfirmBtn.click();
    }

    public void clickCheckBox() {
        wait.waitForClickable(checkbox);
        checkbox.click();
    }

    public void clickConfirmDeliveryBtn() {
        wait.waitForClickable(confirmDeliveryBtn);
        confirmDeliveryBtn.click();
    }

    public void clickPayCheckCheckbox() {
        wait.waitForClickable(payCheckCheckbox);
        payCheckCheckbox.click();
    }

    public void clickThermsAgreeCheckbox() {
        wait.waitForClickable(thermsAgreeCheckbox);
        thermsAgreeCheckbox.click();
    }

    public void clickPlaceOrderBtn() {
        wait.waitForClickable(placeOrderBtn);
        placeOrderBtn.click();
    }
}
