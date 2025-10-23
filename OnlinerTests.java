public class OnlinerTests {
    @Test
    public void cart() {
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
    public void weather() {
        WebDriver chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.get("https://www.onliner.by");

        SoftAssert soft = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(20));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".b-top-navigation")));
        WebElement courses = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("ul[class*=\"helpers_hide_desktop\"] span[class*=\"currency-amount\"]")));
        WebElement weather = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("ul[class*=\"helpers_hide_desktop\"] span[class*=\"js-weather\"]")));
        soft.assertTrue(courses.isDisplayed(), "Курсов нет");
        soft.assertTrue(weather.isDisplayed(), "Погоды нет!");

        System.out.println("Как же заебало");
        soft.assertAll(); // закрыл софтассерт
        chrome.quit();
    }
}
