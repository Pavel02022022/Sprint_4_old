package practicum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    //Кнопка "Заказать"
    private By orderButton = By.className("Button_Button__ra12g");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Нажать кнопку "Заказать"
    public MainPage pressOrederButton(){
        driver.findElement(orderButton).click();
        return this;
    }



}
