package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PriceUtils;

public class OrderHistoryPage {

    @FindBy(css = "tbody tr:first-of-type td:nth-of-type(4) span.bright")
    WebElement paymentStatusLabel;

    @FindBy(css = "tbody tr:first-of-type td:nth-of-type(2)")
    WebElement totalOrderHistoryPrice;
    public OrderHistoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getPaymentStatusLabel(){
       return paymentStatusLabel.getText();
    }

    public double getOrderHistoryPrice(){
        return PriceUtils.parsePrice(totalOrderHistoryPrice.getText());
    }
}
