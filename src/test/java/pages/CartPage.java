package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class CartPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    @FindBy(css = ".checkout .btn-primary")
    WebElement proceedToCheckoutBtn;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WaitUtils(driver);
    }

    public void clickProceedToCheckoutBtn() {
        wait.waitForVisibility(proceedToCheckoutBtn);
        proceedToCheckoutBtn.click();
    }
}
