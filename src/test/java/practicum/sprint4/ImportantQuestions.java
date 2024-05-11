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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@RunWith(Parameterized.class)
public class ImportantQuestions {

    private WebDriver driver;
    private final String question;
    private final String answer;

    private String path = "https://qa-scooter.praktikum-services.ru/";

    public ImportantQuestions(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

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

    @Parameterized.Parameters
    public static Object[][] getQuestionsAndAnswers() {
        //Вопросы и ответы на них
        return new Object[][] {
                { "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов " +
                        "— один за другим."},
                {"Как рассчитывается время аренды?","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы " +
                        "оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области." },

        };
    }

    @Test
    public void test(){
        driver.get(path);

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath(".//div[contains(text(), '" + question + "')]")));






        // Скролл до кнопки
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath(".//div[contains(text(), '" + question + "')]")));


        // Привязывемся к id для дальнейшего поиска текста выпадающего списка
        String id =  driver.findElement(By.xpath(".//div[contains(text(), '" + question + "')]"))
                .getAttribute("aria-controls");
        System.out.println(id);

        // Кликаем по вопросу
        driver.findElement(By.xpath(".//div[contains(text(), '" + question + "')]")).click();

        // Выпадающий список открылся
        Assert.assertEquals("true", driver.findElement(By.xpath(".//div[contains(text(), '" + question + "')]"))
                .getAttribute("aria-expanded"));

        // Текст больше не скрыт
        Assert.assertNull(driver.findElement(By.xpath(".//div[@id ='" + id + "']")).getAttribute("hidden"));


        // Проверка, что открылся соответствующий текст
        String text = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//div[@id ='" + id + "']")))).getText();

        System.out.println(text);
        Assert.assertEquals(text, answer);






    }



    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();

    }

    @Test
    public void test2(){

        driver.get(path);

        // Скролл до кнопки


        List<WebElement> elements = driver.findElements(By.className("accordion__item"));
        System.out.println(elements);



    }
}
