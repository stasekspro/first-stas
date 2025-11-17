package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends pages.BasePage {

    public CartPage(WebDriver chrome) {
        super(chrome);
    }


    @Override
    public boolean isPageLoaded() {
        try {
            String currentUrl = chrome.getCurrentUrl();
            return currentUrl != null && currentUrl.contains("cart.onliner.by");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCartFormDisplayed() {
        try {
            WebElement cartForm = chrome.findElement(By.cssSelector(".cart-form"));
            return cartForm.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}