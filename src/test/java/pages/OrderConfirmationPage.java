package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utils.PriceUtils;
import utils.WaitUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.fail;

public class OrderConfirmationPage {
    private final WebDriver driver;
    private final WaitUtils wait;
    @FindBy(xpath = "//li[@id='order-reference-value']")
    WebElement orderReference;
    @FindBy(css = " table tbody tr:nth-of-type(4) td:nth-of-type(2)")
    WebElement totalValue;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public String getOrderReference() {
        wait.waitForVisibility(orderReference);
        return orderReference.getText().trim();
    }

    public double getTotalValue(){
        return PriceUtils.parsePrice(totalValue.getText());
    }

    public void takeScreenshotOfOrderConfirmation() {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());

            String screenshotFilePath = "target/screenshots/order-confirmation-" + timestamp + ".png";
            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.simple())
                    .takeScreenshot(driver);

            ImageIO.write(screenshot.getImage(), "PNG", new File(screenshotFilePath));
        } catch (IOException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
