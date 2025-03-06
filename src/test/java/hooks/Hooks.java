package hooks;

import drivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.components.HeaderComponent;

public class Hooks {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
        System.out.println("Setup started...");
    }

    @After
    public void tearDown() {
        System.out.println("Tear down started...");
        HeaderComponent headerComponent = new HeaderComponent(DriverManager.getDriver());
        headerComponent.logoutUser();
        DriverManager.quitDriver();
        System.out.println("Tear down finished...");
    }
}
