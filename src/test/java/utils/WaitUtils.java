package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private final WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 10; // Domyślny czas oczekiwania

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Oczekiwanie na widoczność zdefiniowanego elementu
    public WebElement waitForVisibility(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    // Oczekiwanie na klikalność zdefiniowanego elementu
    public WebElement waitForClickable(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    // Oczekiwanie na obecność tekstu w zdefiniowanym elemencie
    public boolean waitForTextToBePresentInElement(WebElement element, String text) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    // Oczekiwanie na konkretną wartość w atrybucie zdefiniowanego elementu
    public boolean waitForAttributeToBe(WebElement element, String attribute, String value) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    // Oczekiwanie na niewidoczność zdefiniowanego elementu
    public boolean waitForElementToBeInvisible(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    // Oczekiwanie na zmianę "wartości live" w polu input - idealne dla Presty!
    public boolean waitForInputValueToChange(WebElement element, String oldValue) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(driver -> { //strzałka to lambda czyli funkacja anonimowa (bez nazwy, dwie klamerki i wytyczne)
                    String currentValue = element.getDomProperty("value");
                    return currentValue != null && !currentValue.equals(oldValue);
                });
    }

    // Pobieranie aktualnej wartości inputa (zamiast getAttribute - prestosafe™)
    public String getLiveInputValue(WebElement element) {
        return element.getDomProperty("value");
    }

}
