package tests;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class OnlinerTests extends BaseTest {

    @Test
    public void OpenNewsDropdownonHover() {
        WebElement news = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("li.b-main-navigation__item:nth-child(1) > span.b-main-navigation__text")
        ));
        actions.moveToElement(news).perform();

        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("li.b-main-navigation__item:nth-child(1) > div.b-main-navigation__dropdown")
        ));
        assertTrue(dropdown.isDisplayed(), "Дропдаун 'Новости' не открылся");
    }


    @Test
    public void OpenAutoDropdownonHover() {
        WebElement auto = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("li.b-main-navigation__item:nth-child(3) span.b-main-navigation__text")));
        actions.moveToElement(auto).perform();

        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.b-main-navigation__dropdown_visible")));
        assertTrue(dropdown.isDisplayed(), "Дропдаун 'Автобарахолка' не открылся");
    }

    @Test
    public void OpenRealtyDropdownonHover() {
        WebElement realty = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("li.b-main-navigation__item:nth-child(5) span.b-main-navigation__text")));
        actions.moveToElement(realty).perform();

        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.b-main-navigation__dropdown_visible")));
        assertTrue(dropdown.isDisplayed(), "Дропдаун 'Дома и квартиры' не открылся");

    }
}
