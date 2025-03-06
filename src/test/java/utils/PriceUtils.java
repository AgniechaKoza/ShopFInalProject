package utils;

import org.openqa.selenium.WebDriver;

public class PriceUtils {
    private WebDriver driver;

    public PriceUtils(WebDriver driver) {
        this.driver = driver;
    }

    public static double parsePrice(String priceText) {
        // Usuwa wszystko co nie jest cyfrą, kropką lub przecinkiem i Zamienia przecinki na kropki (na wypadek formatu PL)
        String cleanPrice = priceText.replaceAll("[^0-9,.]", "").replace(",", ".");
        // Parsuje do double
        return Double.parseDouble(cleanPrice);
    }
}
