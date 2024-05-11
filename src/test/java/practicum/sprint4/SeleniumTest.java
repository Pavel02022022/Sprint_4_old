import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class SeleniumTest {

    private WebDriver driver;
    private String answer;
    private int index;

    @Parameterized.Parameters
    public static Collection<Object[]> getAnswers() {
        return Arrays.asList(new Object[][]{
                {"answer1", 0},
                {"answer2", 1},
                {"answer3", 2}
        });
    }

    public SeleniumTest(String answer, int index) {
        this.answer = answer;
        this.index = index;
    }

    @Test
    public void test2() {
        driver = new ChromeDriver();
        String path = "https://qa-scooter.praktikum-services.ru/";
        driver.get(path);

        // Количество кнопок
        int size = driver.findElements(By.className("accordion__button")).size();
        List<WebElement> buttons = driver.findElements(By.className("accordion__button"));

        for (int i = 0; i < size; i++) {
            if (i == index) {
                String ariaControls = buttons.get(i).getAttribute("aria-controls");
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                        driver.findElement(By.xpath(".//div[@aria-controls='" + ariaControls + "']")));
                buttons.get(i).click();
                // Выпадающий список открылся
                Assert.assertEquals("true", buttons.get(i).getAttribute("aria-expanded"));
                // Текст больше не скрыт
                Assert.assertTrue(buttons.get(i).isDisplayed());
                // Проверка, что открылся соответствующий текст
                String text = new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//div[@id ='" + ariaControls + "']")))).getText().trim();
                Assert.assertEquals(text, answer);
            }
        }

        driver.quit();
    }

}