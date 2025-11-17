package pages;

import org.openqa.selenium.WebDriver;

public class Button extends BaseElement {

    public Button(WebDriver chrome, String locator, String name) {
        super(chrome, locator, name);
    }

    public void click() {
        getElement().click();
        System.out.println("Clicked on button: " + name);
    }
}