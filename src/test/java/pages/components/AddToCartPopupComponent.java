package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PriceUtils;
import utils.WaitUtils;

public class AddToCartPopupComponent {
    private final WebDriver driver;
    private final WaitUtils wait;

    @FindBy(css = "#myModalLabel")
    WebElement successAddToCartMessage;
    @FindBy(className = "size")
    WebElement sizeMessage;
    @FindBy(css = ".product-quantity strong")
    WebElement quantityMessage;
    @FindBy(css = "[class='product-price']")
    WebElement singleCartPrice;
    @FindBy(css = ".cart-content-btn .btn-primary .material-icons")
    WebElement proceedToCheckoutBtn;

    public AddToCartPopupComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public String getAddToCartMessage() {
        wait.waitForVisibility(successAddToCartMessage);
        return successAddToCartMessage.getText();
    }

    public String getSizeMessage() {
        wait.waitForVisibility(sizeMessage);
        return sizeMessage.getText();
    }

    public String getQuantityMessage() {
        wait.waitForVisibility(quantityMessage);
        return quantityMessage.getText();
    }

    public double getSingleCartPrice() {
        wait.waitForVisibility(singleCartPrice);
        return PriceUtils.parsePrice(singleCartPrice.getText());
    }

    public void clickProceedToCheckoutBtn() {
        proceedToCheckoutBtn.click();
    }
}
