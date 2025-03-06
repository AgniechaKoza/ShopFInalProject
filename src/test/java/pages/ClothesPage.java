package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClothesPage {
    private final WebDriver driver;

    @FindBy(css = "[data-id-product='22']")
    WebElement sweaterLink;

    public ClothesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToSweaterSite() {
        sweaterLink.click();
    }
}
