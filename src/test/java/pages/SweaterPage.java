package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.PriceUtils;
import utils.WaitUtils;

public class SweaterPage {
    private final WebDriver driver;
    private final WaitUtils wait;
    private Select select;

    @FindBy(id = "group_1")
    WebElement sizeDropDown;
    @FindBy(id = "quantity_wanted")
    WebElement quantityInput;
    @FindBy(css = "[class*='bootstrap-touchspin-up']")
    WebElement plusQuantityBtn;
    @FindBy(css = "[class*='bootstrap-touchspin-down']")
    WebElement minusQuantityBtn;
    @FindBy(css = ".product-discount span")
    WebElement regularPrice;
    @FindBy(css = "[data-button-action='add-to-cart']")
    WebElement addToCartBtn;

    public SweaterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WaitUtils(driver);
    }

    public void selectSize(String size) {
        select = new Select(sizeDropDown);
        select.selectByVisibleText(size);
    }

    public String getDisplayedSize() {
        wait.waitForVisibility(sizeDropDown);
        return select.getFirstSelectedOption().getText();
    }

    public void selectQuantityUsingButtons(String quantity) {
        int targetQuantity = Integer.parseInt(quantity);
        wait.waitForClickable(plusQuantityBtn);
        wait.waitForClickable(minusQuantityBtn);

        int currentQuantity = Integer.parseInt(wait.getLiveInputValue(quantityInput));
        System.out.printf("Ustawiam ilość sztuk: %d (obecnie w polu: %d)%n", targetQuantity, currentQuantity);

        while (currentQuantity != targetQuantity) {
            if (currentQuantity < targetQuantity) {
                wait.waitForClickable(plusQuantityBtn);
                currentQuantity = changeQuantity(plusQuantityBtn, currentQuantity, quantityInput, targetQuantity);
            } else {
                wait.waitForClickable(minusQuantityBtn);
                currentQuantity = changeQuantity(minusQuantityBtn, currentQuantity, quantityInput, targetQuantity);
            }
            currentQuantity = Integer.parseInt(wait.getLiveInputValue(quantityInput));
        }
        System.out.printf("Finalnie ustawiona ilość: %d%n", currentQuantity);
    }

    // Nie pobieram ilości z okienka metodą setDisplayedSize bo tu Presta bałagani!!!
    public double singleRegularPrice() {
        return PriceUtils.parsePrice(regularPrice.getText());
    }

    public void addToCart() {
        addToCartBtn.click();
    }

    private int changeQuantity(WebElement button, int currentQuantity, WebElement quantityInput, int targetQuantity) {
        String oldValue = wait.getLiveInputValue(quantityInput);
        button.click();
        wait.waitForInputValueToChange(quantityInput, oldValue);   // Czekamy aż wartość faktycznie się zmieni (Presta-safe™)
        int newQuantity = Integer.parseInt(wait.getLiveInputValue(quantityInput));
        System.out.printf("Zmieniam ilość z %s na %s%n", oldValue, newQuantity);
        System.out.printf("Kliknięcie %s, nowa ilość: %d%n", (button.equals(plusQuantityBtn) ? "+" : "-"), newQuantity);
        return newQuantity;
    }
}
