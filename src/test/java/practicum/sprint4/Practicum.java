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

import java.time.Duration;

public class Practicum {
    private WebDriver driver;
    private String path = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setup() {
        // Настройки Хрома
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        // Драйвер для браузера Chrome
        driver = new ChromeDriver(options);
        // Ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void test() throws InterruptedException {
        // Переход на страницу тестового приложения
        driver.get(path);

        MainPage mainPage = new MainPage(driver);



        String aa = driver.findElement(By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[1]/div[1]/div")).getText();
        System.out.println(aa);

        driver.findElement(By.xpath(".//div[contains(text(), 'Сколько это стоит? И как оплатить?')]")).click();
        String bb = driver.findElement(By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[1]/div[2]/p")).getText();
        System.out.println(bb);
        driver.findElement(By.xpath(".//div[contains(text(), 'Хочу сразу несколько самокатов! Так можно?')]")).click();

        // /html/body/div/div/div/div[5]/div[2]/div/div[1]/div[1]/div
        String bbb = driver.findElement(By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[1]/div[2]/p")).getText();
        System.out.println(bbb);



    }
    @Test
    public void asd(){
        driver.get(path);

        String t1 = "Сколько это стоит? И как оплатить?";
        By l1 = By.xpath(".//div[contains(text(), '" + t1 + "')]");

        // Скролл до кнопки
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(l1));
        driver.findElement(By.xpath(".//p[contains(text(), 'Сутки — 400 рублей. Оплата курьеру — наличными или картой.')]"));
        String hidden = driver.findElement(By.xpath(".//p[contains(text(), 'Сутки — 400 рублей. Оплата курьеру — наличными или картой.')]")).getAttribute("hidden");
        String hidden2 = driver.findElement(By.xpath(".//p[contains(text(), 'Сутки — 400 рублей. Оплата курьеру — наличными или картой.')]")).getText();
        System.out.println(hidden);

        driver.findElement(l1).click();
        driver.findElement(By.xpath(".//p[contains(text(), 'Сутки — 400 рублей. Оплата курьеру — наличными или картой.')]"));
    }

    @Test
    public void hidden(){
        driver.get(path);

        String t1 = "Сколько это стоит? И как оплатить?";
        By l1 = By.xpath(".//div[contains(text(), '" + t1 + "')]");
        By l11 = By.className("accordion__panel");

        // Скролл до кнопки
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(l1));
        //driver.findElement(l1).click();

        WebElement accordionPanel = driver.findElement(l11);
        String hiddenAttribute = accordionPanel.getAttribute("hidden");
        System.out.println(hiddenAttribute);
        if (hiddenAttribute != null && hiddenAttribute.equals("true")) {
            System.out.println("The element has the hidden attribute.");
        } else {
            System.out.println("The element does not have the hidden attribute.");
        }
        driver.findElement(l1).click();

        String hiddenAttribute2 = accordionPanel.getAttribute("hidden");
        System.out.println(hiddenAttribute2);
        if (hiddenAttribute2 != null && hiddenAttribute2.equals("true")) {
            System.out.println("The element has the hidden attribute.");
        } else {
            System.out.println("The element does not have the hidden attribute.");
        }

    }

    @Test
    public void texts(){
        driver.get(path);

        String t1 = "Сколько это стоит? И как оплатить?";
        By l1 = By.xpath(".//div[contains(text(), '" + t1 + "')]");
        String tt1 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";

        String t2 = "Хочу сразу несколько самокатов! Так можно?";
        By l2 = By.xpath(".//div[contains(text(), '" + t2 + "')]");
        String tt2 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";


        // Скролл до кнопки
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(l1));

        // Привязывемся к id для дальнейшего поиска текста выпадающего списка
        String id =  driver.findElement(l1).getAttribute("aria-controls");
        //System.out.println(id);

        driver.findElement(l1).click();

        // Выпадающий список открылся
        Assert.assertEquals("true", driver.findElement(l1).getAttribute("aria-expanded"));

        // Текст больше не скрыт
        Assert.assertEquals(null, driver.findElement(By.xpath(".//div[@id ='" + id + "']")).getAttribute("hidden"));


        // Проверка, что открылся соответствующий текст
        String text = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//div[@id ='" + id + "']")))).getText();

        System.out.println(text);
        Assert.assertEquals(text, tt1);









    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
