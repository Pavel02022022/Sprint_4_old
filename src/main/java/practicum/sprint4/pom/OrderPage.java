package practicum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    private By name = By.xpath(".//input[@placeholder='* Имя']");

    private By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    private By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStation = By.xpath(".//input[@placeholder='* * Станция метро']");
    private By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriod = By.className("Dropdown-placeholder");
    private By colorBlack = By.id("black");
    private By colorGrey = By.id("grey");
    private By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By backButton = By.xpath(".//button[contains(text(), 'Назад')]");
    private By orderButton = By.xpath(".//button[contains(text(), 'Заказать')]");
    private By noButton = By.xpath(".//button[contains(text(), 'Нет')]");
    private By yesButton = By.xpath(".//button[contains(text(), 'Да')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage fillFieldName(String text){
        driver.findElement(name).sendKeys(text);
        return this;
    }

    public OrderPage fillFieldSurname(String text){
        driver.findElement(surname).sendKeys(text);
        return this;
    }

    public OrderPage fillFieldAddress(String text){
        driver.findElement(address).sendKeys(text);
        return this;
    }

    public OrderPage fillFieldMetroStation(String text){
        driver.findElement(metroStation).sendKeys(text);
        return this;
    }

    public OrderPage fillFieldPhone(String text){
        driver.findElement(phone).sendKeys(text);
        return this;
    }







}
