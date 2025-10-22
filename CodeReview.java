public class CodeReview {
    public static void main(String[] args) {
        WebDriver chrome = new ChromeDriver();

        chrome.get("https://www.onliner.by");
        WebElement cartElement = (new WebDriverWait(chrome, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.b-top-profile__cart")));
        cartElement.click();
        WebElement catalogLink = chrome.findElement(By.xpath("//a[@href='https://catalog.onliner.by']"));
        assert catalogLink.isDisplayed() : "вот ссылка";
       // разобрался с xpath
        System.out.println("Стасики Wins: " + catalogLink.getText());
        chrome.quit();
    }
}
