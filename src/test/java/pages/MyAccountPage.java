package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {


    @FindBy(xpath = "/html/body//a[@id='address-link']")
    WebElement addressLink;
@FindBy(id = "history-link")
    WebElement orderHistoryLink;

    public MyAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickAddressLink() {
        addressLink.click();
    }

    public void clickOrderHistoryLink(){
        orderHistoryLink.click();
    }
}
