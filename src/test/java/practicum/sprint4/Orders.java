package practicum.sprint4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import practicum.sprint4.pom.MainPage;
import practicum.sprint4.pom.OrderPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class Orders {
    private WebDriver driver;
    // Имя
    private String name;
    // Фамилия
    private String surname;
    // Адрес
    private String address;
    // Метро
    private String metroStation;
    // Номер телефона
    private String phone;
    // Период аренды
    private String rentalPeriod;
    // Цвет самоката
    private String color;
    // Комментарий
    private String comment;
    // Позиция кнопки "Заказать"
    private String orderButtonPosition;

    public Orders(String name, String surname, String address, String metroStation, String phone, String rentalPeriod, String color, String comment, String orderButtonPosition) {
        this.name = name;
        this.surname = surname;
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
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        // Драйвер для браузера Chrome 
        driver = new ChromeDriver(options);
        //driver = new FirefoxDriver();
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        // Имя, Фамилия, адрес, метро, номер телефона, цвет самоката, комментарий, позиция кнопки "Заказать"
        return new Object[][] {
                {"Тест", "Тестов", "ул. Пушкина, д.Колотушкина", "Черкизовская", "79999999999", "двое суток", "чёрный жемчуг", "09:50", "top"}, //Заказ через верхнюю кнопку, черный цвет самоката
                {"Тестан", "Тестович", "ул. Лермонтова, д.52", "Сокольники", "79999999999", "трое суток", "серая безысходность", "Привет", "top"},  //Заказ через нижнюю кнопку, серый цвет самоката
                {"Тест", "Тестов", "ул. Пушкина, д.4", "Черкизовская", "79999999999", "двое суток", "чёрный жемчуг", "09:50", "bottom"}, //Заказ через нижнюю кнопку, черный цвет самоката
                {"Тестан", "Тестович", "ул. Лермонтова, д.52", "Сокольники", "79999999999", "трое суток", "серая безысходность", "Привет", "bottom"}, //Заказ через нижнюю кнопку, серый цвет самоката
                {"Тест", "Тестов", "ул. Сушкина, д.Колотушкина", "Лубянка", "79999999999", "двое суток", null, "09:50", "top"}, //Заказ через верхнюю кнопку, цвет самоката не выбран
                {"Тестик", "Тестов", "ул. Пушкина, д.2", "Черкизовская", "79999999999", "двое суток", null, "09:50", "bottom"}, //Заказ через нижнюю кнопку, цвет самоката не выбран
        };
    }

    // Проверка создания заказа
    @Test
    public void checkOrderExpectCreated()  {
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnOrderButton(orderButtonPosition);

        OrderPage orderPage = new OrderPage(driver);
        String successOrder = orderPage.fillFieldName(name)
                .fillFieldSurname(surname)
                .fillFieldAddress(address)
                .fillMetroStation(metroStation)
                .fillFieldPhone(phone)
                .nextButtonClick()
                .fillRentDate()
                .fillRentalPeriod(rentalPeriod)
                .setCollor(color)
                .fillComment(comment)
                .orderButtonClick()
                .yesButtonClick()
                .orderProcessed();
        Assert.assertEquals("Заказ не был оформлен","Заказ оформлен", successOrder);
    }

    @After
    public void teardown() {driver.quit();} // Закрываем браузер

}
