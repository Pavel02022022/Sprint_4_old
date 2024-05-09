package ru.yandex.practicum.sprint4;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Practicum {
    private WebDriver driver;
    private String path = "https://qa-scooter.praktikum-services.ru/";

    @Test
    public void test() {
        // драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        // переход на страницу тестового приложения
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
