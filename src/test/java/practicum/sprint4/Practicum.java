package practicum.sprint4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import practicum.sprint4.pom.MainPage;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Practicum {
    private WebDriver driver;
    private String path = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setup() {
        // Настройки Хрома
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        // Драйвер для браузера Chrome
        driver = new ChromeDriver(options);
        // Ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void test() {
        // Переход на страницу тестового приложения
        driver.get(path);

        MainPage mainPage = new MainPage(driver);

        mainPage.pressOrederButton();

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
