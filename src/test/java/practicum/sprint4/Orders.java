package practicum.sprint4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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

public class Orders {
    private WebDriver driver;

    @Before
    public void setup() {
        // Настройки Хрома
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        // Драйвер для браузера Chrome
        driver = new ChromeDriver(options);
        // driver = new FirefoxDriver();
        // Ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // Адрес страницы
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers() {
        // Номер accordion__button, вопросы и ответы на них
        return new Object[][] {
                {"Тест", "Тестов", "ул. Пушкина, д.Колотушкина", "Черкизовская", "79999999999", "двое суток", "чёрный жемчуг", "09:50"},
                {"Тест2", "Тестов2", "ул. Лермонтова, д.52", "Сокольники", "79999999999", "троек суток", "серая безысходность", "Привет>"},
        };
    }


    // Проверка количества кнопок "Заказать"
    @Test
    public void checkOrderButtonQuantity(){
        MainPage mainPage = new MainPage(driver);
        Assert.assertEquals(2, mainPage.getQuantityOfOrderButtons());
    }


    @Test
    public void checkTopOrderButton()  {
        MainPage mainPage = new MainPage(driver);

        //mainPage.goToBottomOrderButton().clickOnBottomOrderButton();
        mainPage.goToTopOrderButton().clickOnTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);

       String successOrder = orderPage.fillFieldName("Ававыа")
                 .fillFieldSurname("ВАвав")
                 .fillFieldAddress("Вавава")
                 .fillMetroStation("Черкизовская")
                 .fillFieldPhone("79999999999")
                 .nextButtonClick()
                 .fillRentDate()
                 .fillRentalPeriod("двое суток")
                 .setCollor("чёрный жемчуг")
                 .fillComment("fg")
                 .orderButtonClick().yesButtonClick()
                 .orderProcessed();
       Assert.assertEquals("Заказ оформлен", successOrder);
        //System.out.println(driver.findElement(By.cssSelector(".Order_ModalHeader__3FDaJ > div:first-child")));


    }

    @Test
    public void checkBottomOrderButton()  {
        MainPage mainPage = new MainPage(driver);

        mainPage.goToBottomOrderButton().clickOnBottomOrderButton();

        OrderPage orderPage = new OrderPage(driver);

        String successOrder = orderPage.fillFieldName("Ававыа")
                .fillFieldSurname("ВАвав")
                .fillFieldAddress("Вавава")
                .fillMetroStation("Черкизовская")
                .fillFieldPhone("79999999999")
                .nextButtonClick()
                .fillRentDate()
                .fillRentalPeriod("двое суток")
                .setCollor("чёрный жемчуг")
                .fillComment("fg")
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
