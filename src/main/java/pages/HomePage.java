package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private final Button cartButton;

    public HomePage(WebDriver chrome) {
        super(chrome);
        cartButton = new Button(chrome, "a.auth-bar__item--cart", "Иконка корзины");
    }


    @Override
    public boolean isPageLoaded() {
        try {
            WebElement topNavigation = chrome.findElement(By.cssSelector(".b-top-navigation"));
            return topNavigation.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public pages.CartPage openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.auth-bar__item--cart")));
        cartButton.click();

        wait.until(ExpectedConditions.urlContains("cart.onliner.by"));

        return new pages.CartPage(chrome);
    }
}