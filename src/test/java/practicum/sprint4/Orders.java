package practicum.sprint4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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


    @Test
    public void checkOrderButton() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);

        //mainPage.goToBottomOrderButton().clickOnBottomOrderButton();
        mainPage.goToTopOrderButton().clickOnTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.fillFieldName("Ававыа")
                 .fillFieldSurname("ВАвав")
                 .fillFieldAddress("Вавава")
                 .fillMetroStation("Черкизовская")
                 .fillFieldPhone("79999999999")
                 .nextButtonClick()
                 .fillRentDate()
                 .fillRentalPeriod("двое суток")
                 .setCollor("чёрный жемчуг")
                 .fillComment("fg")
                 .orderButtonClick();

        //driver.findElement(By.id("black")).click();
        //driver.findElement(By.id("grey")).click();




        //.fillRentDate()
                 //.fillRentalPeriod();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class = 'Input_InputContainer__3NykH']/input[@placeholder='Комментарий для курьера']")));

        //.fillComment("sfg")
          //      .orderButtonClick();



    }


    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }


}
