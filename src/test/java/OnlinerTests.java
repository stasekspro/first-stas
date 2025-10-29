import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.asserts.SoftAssert; // если используешь SoftAssert

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OnlinerTests {
    @Test
    public void OpenCartPageWhenCartIconClicked() {
        WebDriver chrome = new ChromeDriver();

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
        chrome.quit();
    }

    @Test
    public void DisplayCurrencyAndWeatherOnHomePage() {
        WebDriver chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.get("https://www.onliner.by");

        SoftAssert soft = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(20));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".b-top-navigation")));
        WebElement courses = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("ul[class*=\"helpers_hide_desktop\"] span[class*=\"currency-amount\"]")));
        WebElement weather = chrome.findElement(
                By.cssSelector("ul[class*=\"helpers_hide_desktop\"] span[class*=\"js-weather\"]"));
        soft.assertTrue(courses.isDisplayed(), "Курсов нет");
        soft.assertTrue(weather.isDisplayed(), "Погоды нет!");

        System.out.println("Как же заебало");
        soft.assertAll(); // закрыл софтассерт
        chrome.quit();
    }


    @Test
    public void DisplayCatalogonHomePage() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"); // здесь у меня магическим образом идея открывала edge, пришлось гуглить
        WebDriver chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.get("https://www.onliner.by");
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.project-navigation__list.project-navigation__list_secondary")));


        List<WebElement> items = chrome.findElements(By.cssSelector("ul.project-navigation__list.project-navigation__list_secondary li a"));
        List<String> actual = new ArrayList<>();

        for (WebElement element : items) {
            String text = element.getText().trim();
            if (!text.isEmpty()) {
                actual.add(text);  // здесь помогал чатгпт, откровенно
            }
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
                "Видеокарты"
        );

        Assertions.assertEquals(expected, actual, "Элементы меню отличаются от ожидаемых!");

        chrome.quit();
    }
}
