package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderComponent {
    private final WebDriver driver;
    @FindBy(css = "[title='Log in to your customer account']")
    WebElement signInBtn;
    @FindBy(className = "account")
    WebElement loggedUserName;
    @FindBy(className = "logout")
    WebElement logoutBtn;
    @FindBy(id = "category-3")
    WebElement clothesLink;

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signInClick() {
        signInBtn.click();
    }

    public String getLoggedUserName() {
        return loggedUserName.getText();
    }

    public void clickClothesLink() {
        clothesLink.click();
    }

    public void logoutUser() {
        if (logoutBtn.isEnabled()) {
            logoutBtn.click();
        }
    }

}
