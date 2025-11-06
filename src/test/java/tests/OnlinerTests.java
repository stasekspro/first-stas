package tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OnlinerTests extends BaseTest {
    @Test
    public void OpenCartPageWhenCartIconClicked() {

        chrome.get("https://www.onliner.by");
        WebElement cartElement = (new WebDriverWait(chrome, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.b-top-profile__cart")));
        cartElement.click();
        (new WebDriverWait(chrome, Duration.ofSeconds(10)))
                .until(ExpectedConditions.urlContains("cart.onliner.by"));
        WebElement cartForm = (new WebDriverWait(chrome, Duration.ofSeconds(10)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-form")));
        assert cartForm.isDisplayed() : "Корзина не открыта";
        // поменял xpath на css селектор, где по переходу в корзину тест убедился, что я тут
        System.out.println("Стасики Wins: " + cartForm.getText());
    }

    @Test
    public void DisplayCurrencyAndWeatherOnHomePage() {
        chrome.get("https://www.onliner.by");

        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(20));
                             wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".b-top-navigation")));
        WebElement courses = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("ul[class*=\"helpers_hide_desktop\"] span[class*=\"currency-amount\"]")));
        WebElement weather = chrome.findElement(
                By.cssSelector("ul[class*=\"helpers_hide_desktop\"] span[class*=\"js-weather\"]"));
        Assertions.assertAll(
                () -> Assertions.assertTrue(courses.isDisplayed(), "Курсов нет"),
                () -> Assertions.assertTrue(weather.isDisplayed(), "Погоды нет!"));

        System.out.println("Как же заебало");
    }


    @Test
    public void DisplayCatalogMenuButtonsonHomePage() {
        chrome.get("https://www.onliner.by");

        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.project-navigation__list.project-navigation__list_secondary")));

        List<WebElement> items = chrome.findElements(By.cssSelector("ul.project-navigation__list.project-navigation__list_secondary li a"));
        List<String> actual = new ArrayList<>();
        for (WebElement element : items) {
            actual.add(element.getText().trim());
        }

        List<String> expected = Arrays.asList(
                "Apple iPhone",
                "Мобильные телефоны",
                "Автомобильные шины",
                "Телевизоры",
                "Стиральные машины",
                "Холодильники",
                "Ноутбуки",
                "Мониторы",
                "Видеокарты",
                "Планшеты"
        );

        Assertions.assertEquals(expected, actual, "Элементы меню отличаются от ожидаемых!");

        chrome.quit();
    }
}
