package practicum.sprint4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import practicum.sprint4.pom.MainPage;

import java.time.Duration;

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
    public void checkOrderButton(){
        MainPage mainPage = new MainPage(driver);

        mainPage.goToBottomOrderButton().clickOnBottomOrderButton();
        mainPage.goToTopOrderButton().clickOnTopOrderButton();

    }


    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }


}
