package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    public CartPage(WebDriver chrome) {
        super(chrome);
    }


    @Override
    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-form")));
            return true;
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