package practicum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderPage {
    private WebDriver driver;

    // Поле для ввода Имени
    private By name = By.xpath(".//input[@placeholder='* Имя']");
    // Поле для ввода Фамилии
    private By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле для ввода Адреса доставки
    private By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле для выбора станции метро
    private By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    // Поле для ввода номера телефона
    private By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Поле для выбора даты доставки
    private By deliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле для выбора срока аренды
    private By rentalPeriod = By.className("Dropdown-placeholder");
    // Чек-бокс для выбора цвета
    private By colorBlack = By.id("black");
    // Чек-бокс для выбора цвета
    private By colorGrey = By.id("grey");
    // Поле для ввода комментария
    private By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка назад
    private By backButton = By.xpath(".//button[contains(text(), 'Назад')]");
    // Кнопка для оформления заказа
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    // Кнопка "Нет"
    private By noButton = By.xpath(".//button[contains(text(), 'Нет')]");
    // Кнопка "Да"
    private By yesButton = By.xpath(".//button[contains(text(), 'Да')]");
    // Кнопка "Далее"
    private By nextButton = By.xpath(".//button[contains(text(), 'Далее')]");

    //private By orderProcessed = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");
    // Модальное окно успешного заказа
    private  By orderProcessed = By.className("Order_ModalHeader__3FDaJ");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнение поля "Имя"
    public OrderPage fillFieldName(String text){
        driver.findElement(name).sendKeys(text);
        return this;
    }

    // Заполнение поля "Фамилия"
    public OrderPage fillFieldSurname(String text){
        driver.findElement(surname).sendKeys(text);
        return this;
    }

    // Заполнение поля "Адрес"
    public OrderPage fillFieldAddress(String text){
        driver.findElement(address).sendKeys(text);
        return this;
    }

    // Заполнение поля "Телефон"
    public OrderPage fillFieldPhone(String text){
        driver.findElement(phone).sendKeys(text);
        return this;
    }

    // Выбор даты аренды, всегда на день вперед
    public OrderPage fillRentDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String rentDate = LocalDate.now().plusDays(1).format(formatter);
        driver.findElement(deliveryDate).click();
        driver.findElement(deliveryDate).sendKeys(rentDate);
        driver.findElement(deliveryDate).sendKeys(Keys.ENTER);
        return this;
    }

    // Выбор срока аренды
    public OrderPage fillRentalPeriod(String period){
        waitForElement(rentalPeriod);
        driver.findElement(rentalPeriod).click();
        driver.findElement(By.xpath(".//div[text()= '" + period + "']")).click();
        return this;
    }

    // Выбор станции метро
    public OrderPage fillMetroStation(String metroName){
        driver.findElement(metroStation).click();
        driver.findElement(metroStation).sendKeys(metroName);
        waitForElement(By.xpath(".//div[text()= '" + metroName + "']"));
        driver.findElement(By.xpath(".//div[text()= '" + metroName + "']")).click();
        return this;
    }

    // Нажатие на кнопку далее
    public OrderPage nextButtonClick(){
        driver.findElement(nextButton).click();
        return this;
    }

    public void waitAndScrollToElement(By element){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(element)));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(element));
    }

    // Ожидание для появления элемента
    public void waitForElement(By element){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    // Нажатие на кнопку оформления заказа
    public OrderPage orderButtonClick(){
        driver.findElement(orderButton).click();
        return this;
    }

    // Выбор цвета через чек-бокс
    public OrderPage setCollor(String color){
        if (color.equals("чёрный жемчуг")) {
            driver.findElement(colorBlack).click();
        }

        if (color.equals("серая безысходность")) {
            driver.findElement(colorGrey).click();}
        return this;
    }

    // Заполнение поля "Комментарий"
    public OrderPage fillComment(String text){
        driver.findElement(comment).sendKeys(text);
        return this;
    }

    // Нажатие на кнопку "Да"
    public OrderPage yesButtonClick(){
        driver.findElement(yesButton).click();
        return this;
    }

    // Получение текста статуса заказа
    public String orderProcessed(){
        String orderText =  driver.findElement(orderProcessed).getText();
        String[] lines = orderText.split("\n");
        System.out.println(lines[0]);
        return lines[0];

    }





}
