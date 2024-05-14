package practicum.sprint4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import practicum.sprint4.pom.MainPage;
import practicum.sprint4.pom.OrderPage;

import java.time.Duration;


public class Orders2 {
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
        // Адрес страницы
        driver.get("https://qa-scooter.praktikum-services.ru/");


    }

    // Проверка количества кнопок "Заказать"
    @Test
    public void checkOrderButtonQuantity(){
        MainPage mainPage = new MainPage(driver);
        Assert.assertEquals(2, mainPage.getQuantityOfOrderButtons());
    }

    // Проверка заказа через верхнюю кнопку заказа

    @Test
    public void checkOrderCreation2()  {
        MainPage mainPage = new MainPage(driver);

        //mainPage.goToBottomOrderButton().clickOnBottomOrderButton();
        //mainPage.clickOnOrderButton("bottom");
        By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(orderButton));
        driver.findElement(orderButton).click();


        OrderPage orderPage = new OrderPage(driver);

        String successOrder = orderPage.fillFieldName("впапваппвв")
                .fillFieldSurname("ываываыва")
                .fillFieldAddress("ваываывавы")
                .fillMetroStation("Черкизовская")
                .fillFieldPhone("11111111111")
                .nextButtonClick()
                .fillRentDate()
                .fillRentalPeriod("двое суток")
                .setCollor("серая безысходность")
                .fillComment("dsgs")
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
