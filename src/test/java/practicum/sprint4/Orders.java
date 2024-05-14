package practicum.sprint4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practicum.sprint4.pom.MainPage;
import practicum.sprint4.pom.OrderPage;

import java.time.Duration;
import java.time.Instant;

@RunWith(Parameterized.class)
public class Orders {
    private WebDriver driver;

    private String name;
    private String surmane;
    private String address;
    private String metroStation;
    private String phone;
    private String rentalPeriod;
    private String color;
    private String comment;
    private String orderButtonPosition;

    public Orders(String name, String surname, String address, String metroStation, String phone, String rentalPeriod, String color, String comment, String orderButtonPosition) {
        this.name = name;
        this.surmane = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
        this.orderButtonPosition = orderButtonPosition;
    }

    @Before
    public void setup() {
        // Настройки Хрома
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        // Драйвер для браузера Chrome
        driver = new ChromeDriver(options);
        //driver = new FirefoxDriver();
        // Ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // Адрес страницы
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers() {
        // Номер accordion__button, вопросы и ответы на них
        return new Object[][] {
                {"Тест", "Тестов", "ул. Пушкина, д.Колотушкина", "Черкизовская", "79999999999", "двое суток", null, "09:50", "top"},
                {"Тест", "Тестов", "ул. Пушкина, д.Колотушкина", "Черкизовская", "79999999999", "двое суток", "чёрный жемчуг", "09:50", "top"},
                {"Тестан", "Тестович", "ул. Лермонтова, д.52", "Сокольники", "79999999999", "трое суток", "серая безысходность", "Привет", "top"},
                {"Тест", "Тестов", "ул. Пушкина, д.Колотушкина", "Черкизовская", "79999999999", "двое суток", "чёрный жемчуг", "09:50", "bottom"},
                {"Тестан", "Тестович", "ул. Лермонтова, д.52", "Сокольники", "79999999999", "трое суток", "серая безысходность", "Привет", "bottom"},
                {"Тест", "Тестов", "ул. Пушкина, д.Колотушкина", "Черкизовская", "79999999999", "двое суток", null, "09:50", "top"},
                {"Тест2", "Тестов2", "ул. Лермонтова, д.52", "Сокольники", "79999999999", "трое суток", "серая безысходность", "Привет", "bottom"},
        };
    }

    // Проверка количества кнопок "Заказать"
    @Test
    public void checkOrderButtonQuantity(){
        MainPage mainPage = new MainPage(driver);
        Assert.assertEquals(2, mainPage.getQuantityOfOrderButtons());
    }

    // Проверка заказа через верхнюю кнопку заказа


    @Test
    public void checkOrderCreation()  {
        MainPage mainPage = new MainPage(driver);

        //mainPage.goToBottomOrderButton().clickOnBottomOrderButton();
        mainPage.goToTopOrderButton().clickOnOrderButton(orderButtonPosition);

        OrderPage orderPage = new OrderPage(driver);

        String successOrder = orderPage.fillFieldName(name)
                .fillFieldSurname(surmane)
                .fillFieldAddress(address)
                .fillMetroStation(metroStation)
                .fillFieldPhone(phone)
                .nextButtonClick()
                .fillRentDate()
                .fillRentalPeriod(rentalPeriod)
                .setCollor(color)
                .fillComment(comment)
                .orderButtonClick().yesButtonClick()
                .orderProcessed();
        Assert.assertEquals("Заказ оформлен", successOrder);
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }


}
