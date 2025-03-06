package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAddressesPage {
    private final WebDriver driver;

    @FindBy(className = "alert-success")
    WebElement alertField;
    @FindBy(css = ".address-body h4")
    WebElement aliasField;
    @FindBy(xpath = "//div[@class='address-body']/address")
    WebElement addressBlockField;

    public MyAddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String successMessageText() {
        return alertField.getText().trim();
    }

    public String displayedAlias() {
        return aliasField.getText().trim();
    }

    public String displayedAddressBlockText() {

        return addressBlockField.getText().trim();
    }
}


