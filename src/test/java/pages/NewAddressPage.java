package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NewAddressPage {
    private final WebDriver driver;

    @FindBy(css = "#field-alias")
    WebElement aliasInput;
    @FindBy(css = "#field-address1")
    WebElement addressInput;
    @FindBy(css = "#field-city")
    WebElement cityInput;
    @FindBy(css = "#field-postcode")
    WebElement postalCodeInput;
    @FindBy(name = "id_country")
    WebElement countryDropDown;
    @FindBy(id = "field-phone")
    WebElement phoneInput;
    @FindBy(className = "form-control-submit")
    WebElement saveBtn;

    public NewAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterAddressData(String alias, String address, String city, String postal_code, String country, String phone) {
        aliasInput.click();
        aliasInput.clear();
        aliasInput.sendKeys(alias);

        addressInput.click();
        addressInput.clear();
        addressInput.sendKeys(address);

        cityInput.click();
        cityInput.clear();
        cityInput.sendKeys(city);

        postalCodeInput.click();
        postalCodeInput.clear();
        postalCodeInput.sendKeys(postal_code);

        selectCountry(country);

        phoneInput.click();
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        saveBtn.click();
    }

    public void selectCountry(String country) {
        Select select = new Select(countryDropDown);
        select.selectByVisibleText(country);
    }
}
