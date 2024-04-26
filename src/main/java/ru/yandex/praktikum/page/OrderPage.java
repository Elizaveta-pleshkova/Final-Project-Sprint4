package ru.yandex.praktikum.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OrderPage {
    private final WebDriver webDriver;

    //Поле ввода "Имя"
    private final By nameInputLocator = By.xpath("//input[@placeholder='* Имя']");

    //Поле ввода "Фамилия"
    private final By lastnameInputLocator = By.xpath("//input[@placeholder='* Фамилия']");

    //Поле ввода "Адрес"
    private final By addressInputLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    //Поле ввыбора "Станция метро"
    private final By stationInputLocator = By.xpath("//input[@placeholder='* Станция метро']");

    //Поле ввода "Телефон"
    private final By phoneInputLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка "Далее" на форме оформление заказа
    private final By nextButtonLocator = By.xpath("//div[@class='Order_NextButton__1_rCA']//button[text()='Далее']");

    //Поле ввода "Даты"
    private final By dateInputLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");

    //Поле выбора "Срок аренды"
    private final By rentalInputLocator = By.xpath("//div[text()='* Срок аренды']/parent::*");

    //Поле выбора "Цвет самоката"
    private final By colorInputLocator = By.xpath("//div[text()='Цвет самоката']");

    //Поле ввода "Комментарий"
    private final By commentInputLocator = By.xpath("//input[@placeholder='Комментарий для курьера']");

    //Кнопка "Заказать" на форме оформление заказа
    private final By orderButtonLocator = By.xpath("//div[contains(@class, 'Order_Buttons__1xGrp')]//button[text()='Заказать']");

    //Кнопка подтверждения оформления заказа
    private final By orderOrderAcceptLocator = By.xpath("//div[contains(@class, 'Order_Modal__YZ-d3')]//div[text()='Хотите оформить заказ?']/parent::*//div[contains(@class, 'Order_Buttons__1xGrp')]//button[text()='Да']");

    //Окно о подтверждении оформления заказа
    private final By orderFinalLocator = By.xpath("//div[contains(@class, 'Order_Modal__YZ-d3')]//div[text()='Заказ оформлен']");

    //Гиперссылка Самокат
    private final By hyperlinkedScooterLocator = By.xpath("//img[@alt='Scooter']");

    //Главная страница Самокат
    private final String homePageScooterLocator = "https://qa-scooter.praktikum-services.ru/";

    //Строка форматирования для поиска станции
    private final String stationItemLocator = "//div[text()='%s']";

    //Строка форматирования для поиска срока аренды
    private final String rentalPeriodLocator = "//div[@class='Dropdown-menu']//div[text()='%s']";

    //Строка форматирования для поиска цвета самоката
    private final String colorItemLocator = "//input[@id='%s']";

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public void firstPageCustomerInfo(String name, String lastname, String address, String station, String phone){
        WebElement customerInfoName = webDriver.findElement(nameInputLocator);
        customerInfoName.sendKeys(name);

        WebElement customerInfoLastname = webDriver.findElement(lastnameInputLocator);
        customerInfoLastname.sendKeys(lastname);

        WebElement customerInfoAddress = webDriver.findElement(addressInputLocator);
        customerInfoAddress.sendKeys(address);

        WebElement customerInfoStation = webDriver.findElement(stationInputLocator);
        customerInfoStation.click();
        WebElement stationMenu = webDriver.findElement(By.xpath(String.format(stationItemLocator, station)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", stationMenu);
        stationMenu.click();

        WebElement customerInfoPhone = webDriver.findElement(phoneInputLocator);
        customerInfoPhone.sendKeys(phone);
    }

    public void clickNextButton(){
        WebElement nextButton = webDriver.findElement(nextButtonLocator);
        nextButton.click();
    }

    public void secondPageCustomerInfo(String date, String rental, String color, String comment) {
        WebElement customerInfoDate = webDriver.findElement(dateInputLocator);
        customerInfoDate.sendKeys(date);
        customerInfoDate.sendKeys(Keys.RETURN);

        WebElement customerInfoRental = webDriver.findElement(rentalInputLocator);
        customerInfoRental.click();
        WebElement rentalMenu = webDriver.findElement(By.xpath(String.format(rentalPeriodLocator, rental)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", rentalMenu);
        rentalMenu.click();

        WebElement customerInfoColor = webDriver.findElement(colorInputLocator);
        WebElement colorItem = customerInfoColor.findElement(By.xpath(String.format(colorItemLocator,color)));
        colorItem.click();

        WebElement customerInfoComment = webDriver.findElement(commentInputLocator);
        customerInfoComment.sendKeys(comment);
    }

    public void clickOrderButton(){
        WebElement orderButton = webDriver.findElement(orderButtonLocator);
        orderButton.click();
    }

    public void clickConfirmationButton() {
        WebElement acceptButton = webDriver.findElement(orderOrderAcceptLocator);
        new WebDriverWait(webDriver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(acceptButton));
        acceptButton.click();
    }

    public boolean orderAccepted() {
        WebElement orderAccept = webDriver.findElement(orderFinalLocator);
        return orderAccept.isDisplayed();
    }

    public void clickHyperlinkedScooter(){
        WebElement HyperlinkedScooter = webDriver.findElement(hyperlinkedScooterLocator);
        HyperlinkedScooter.click();
    }

    public boolean homePageScooterOpen() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return Objects.equals(webDriver.getCurrentUrl(), homePageScooterLocator);
    }
}
