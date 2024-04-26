package ru.yandex.praktikum.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPage {
    private final WebDriver webDriver;

    //Кнопка принятия cookies
    private final By cookiesButtonLocator = By.id("rcc-confirm-button");
    //Кнопка заказть в шапке страницы
    private final By headerCreateOrderButtonLocator = By.xpath("//div[contains(@class, 'Header')]/button[text()='Заказать']");
    //Кнопка заказть в теле страницы
    private final By homeCreateOrderButtonLocator = By.xpath("//div[contains(@class, 'Home')]/button[text()='Заказать']");

    //Гиперссылка Яндекс
    private final By hyperlinkedYandexLocator = By.xpath("//img[@alt='Yandex']");

    //Главная страница Яндекс
    private final By homePageYandexLocator = By.xpath("//a[@href='/']");

    //Кнопка "Статус заказа"
    private final By orderStatusLocator = By.xpath("//div[@class='Header_Nav__AGCXC']//button[text()='Статус заказа']");

    //Поле ввода номера заказа
    private final By orderInputNumberLocator = By.xpath("//input[@placeholder='Введите номер заказа']");

    //Кнопка "Go!"
    private final By buttonGoLocator = By.xpath("//button[text()='Go!']");

    //Изображение "NotFound"
    private final By imgNotFoundLocator = By.xpath("//img[@alt='Not found']");

    //Строка форматирования для поиска вопроса в разделе «Вопросы о важном»
    private final String qestionLocator = "accordion__heading-%s";
    //Строка форматирования для поиска ответа в разделе «Вопросы о важном»
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";





    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void closeCookiesWindow(){
        webDriver.findElement(cookiesButtonLocator).click();
    }

    public void expandQuestion(int index) {
        WebElement expandQuestion = webDriver.findElement(By.id(String.format(qestionLocator, index)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", expandQuestion);
        new WebDriverWait(webDriver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(expandQuestion));
        expandQuestion.click();
    }

    public boolean answerIsDisplayed(String expectedAnswer) {
        WebElement answerIsDisplayed = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return answerIsDisplayed.isDisplayed();
    }

    public void clickHeaderCreateButton(){
        WebElement createHeaderOrderButton = webDriver.findElement(headerCreateOrderButtonLocator);
        createHeaderOrderButton.click();
    }

    public void clickHomeCreateButton(){
        WebElement createHomeOrderButton = webDriver.findElement(homeCreateOrderButtonLocator);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", createHomeOrderButton);
        createHomeOrderButton.click();
    }

    public void clickHyperlinkedYandex() {
        WebElement hyperlinkedYandex = webDriver.findElement(hyperlinkedYandexLocator);
        hyperlinkedYandex.click();
    }

    public boolean homePageYandexOpen() {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement homePageYandex = webDriver.findElement(homePageYandexLocator);
        return homePageYandex.isDisplayed();
    }

    public void clickOrderStatus(){
        WebElement orderStatusL = webDriver.findElement(orderStatusLocator);
        orderStatusL.click();
    }

    public void inputOrderNumber(String orderNumber){
        WebElement inputOrderNumber = webDriver.findElement(orderInputNumberLocator);
        inputOrderNumber.sendKeys(orderNumber);
    }

    public void clickGoButton(){
        WebElement clickGoButton = webDriver.findElement(buttonGoLocator);
        new WebDriverWait(webDriver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(clickGoButton));
        clickGoButton.click();
    }

    public boolean imgNotFoundIsDisplayed() {
        WebElement imgNotFoundIsDisplayed = webDriver.findElement(imgNotFoundLocator);
        return imgNotFoundIsDisplayed.isDisplayed();
    }
}
