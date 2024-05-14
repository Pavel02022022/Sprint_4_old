package practicum.sprint4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import practicum.sprint4.pom.MainPage;
import practicum.sprint4.pom.OrderPage;

import java.time.Duration;

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
        //driver = new ChromeDriver(options);
        driver = new FirefoxDriver();
        // Ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        // Номер accordion__button, вопросы и ответы на них
        return new Object[][] {

                {"Тест", "Тестов", "ул. Пушкина, д.Колотушкина", "Черкизовская", "79999999999", "двое суток", "чёрный жемчуг", "09:50", "top"}, //Заказ через верхнюю кнопку, черный цвет самоката
                {"Тестан", "Тестович", "ул. Лермонтова, д.52", "Сокольники", "79999999999", "трое суток", "серая безысходность", "Привет", "top"},  //Заказ через нижнюю кнопку, серый цвет самоката
                {"Тест", "Тестов", "ул. Пушкина, д.4", "Черкизовская", "79999999999", "двое суток", "чёрный жемчуг", "09:50", "bottom"}, //Заказ через нижнюю кнопку, черный цвет самоката
                {"Тестан", "Тестович", "ул. Лермонтова, д.52", "Сокольники", "79999999999", "трое суток", "серая безысходность", "Привет", "bottom"}, //Заказ через нижнюю кнопку, серый цвет самоката
                {"Тест", "Тестов", "ул. Сушкина, д.Колотушкина", "Лубянка", "79999999999", "двое суток", null, "09:50", "top"}, //Заказ через верхнюю кнопку, цвет самоката не выбран
                {"Тестик", "Тестов", "ул. Пушкина, д.2", "Черкизовская", "79999999999", "двое суток", null, "09:50", "bottom"}, //Заказ через нижнюю кнопку, цвет самоката не выбран
        };
    }

    // Проверка количества кнопок "Заказать" на главной странице
    @Test
    public void checkOrderButtonQuantity(){
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        Assert.assertEquals("Количество кнопок 'Заказать'!=2",2, mainPage.getQuantityOfOrderButtons());
    }

    // Проверка создания заказа
    @Test
    public void checkOrderCreation()  {
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .clickOnOrderButton(orderButtonPosition);

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
                .orderButtonClick()
                .yesButtonClick()
                .orderProcessed();
        Assert.assertEquals("Заказ не был оформлен","Заказ оформлен", successOrder);
    }

    // Закрываем браузер
    @After
    public void teardown() {driver.quit();}


}
