package practicum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderPage {
    private WebDriver driver;

    private By name = By.xpath(".//input[@placeholder='* Имя']");
    private By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    private By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    private By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By deliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriod = By.className("Dropdown-placeholder");
    private By rentalPeriodMenu = By.className("Dropdown-menu");
    private By colorBlack = By.id("black");
    private By colorGrey = By.id("grey");
    private By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By backButton = By.xpath(".//button[contains(text(), 'Назад')]");
    private By orderButton = By.xpath(".//button[contains(text(), 'Заказать')]");
    private By noButton = By.xpath(".//button[contains(text(), 'Нет')]");
    private By yesButton = By.xpath(".//button[contains(text(), 'Да')]");
    private By nextButton = By.xpath(".//button[contains(text(), 'Далее')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage fillFieldName(String text){
        driver.findElement(name).sendKeys(text);
        return this;
    }

    public OrderPage fillFieldSurname(String text){
        driver.findElement(surname).sendKeys(text);
        return this;
    }

    public OrderPage fillFieldAddress(String text){
        driver.findElement(address).sendKeys(text);
        return this;
    }

    public OrderPage fillFieldMetroStation(String text){
        driver.findElement(metroStation).sendKeys(text);
        return this;
    }

    public OrderPage fillFieldPhone(String text){
        driver.findElement(phone).sendKeys(text);
        return this;
    }


    public OrderPage fillRentDate(){
        //waitAndScrollToElement(deliveryDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String rentDate = LocalDate.now().plusDays(1).format(formatter);
        driver.findElement(deliveryDate).click();
        driver.findElement(deliveryDate).sendKeys(rentDate);
        driver.findElement(deliveryDate).sendKeys(Keys.ENTER);
        return this;
    }


    public OrderPage fillRentalPeriod(String period){
        waitForElement(rentalPeriod);
        driver.findElement(rentalPeriod).click();
        driver.findElement(By.xpath(".//div[text()= '" + period + "']")).click();
        return this;
    }

    public OrderPage fillMetroStation(String metroName){
        driver.findElement(metroStation).click();
        driver.findElement(metroStation).sendKeys(metroName);
        waitForElement(By.xpath(".//div[text()= '" + metroName + "']"));
        driver.findElement(By.xpath(".//div[text()= '" + metroName + "']")).click();
        return this;
    }

    public OrderPage nextButtonClick(){
        driver.findElement(nextButton).click();
        return this;
    }

    public void waitAndScrollToElement(By element){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(element)));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(element));
    }

    public void waitForElement(By element){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    public OrderPage orderButtonClick(){
        driver.findElement(orderButton).click();
        return this;
    }

    public OrderPage setBlackCollor(){
        driver.findElement(colorBlack).click();
        return this;
    }
    public OrderPage setGreyCollor(){
        driver.findElement(colorGrey).click();
        return this;
    }

    public OrderPage setCollor(String color){
        if (color.equals("чёрный жемчуг")) {
            driver.findElement(colorBlack).click();
        }

        if (color.equals("серая безысходность")) {
            driver.findElement(colorGrey).click();}

        return this;
    }


    public OrderPage fillComment(String text){
        waitAndScrollToElement(comment);
        driver.findElement(comment).sendKeys(text);
        return this;
    }






}
