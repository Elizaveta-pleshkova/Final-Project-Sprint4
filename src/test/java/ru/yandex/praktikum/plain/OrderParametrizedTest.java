package ru.yandex.praktikum.plain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderParametrizedTest {
    private WebDriver webDriver;
    private static final String BROWSER = "firefox";

    private String name;
    private String lastname;
    private String address;
    private String station;
    private String phone;
    private String date;
    private String rental;
    private String color;
    private String comment;

    public OrderParametrizedTest(String name, String lastname, String address, String station, String phone, String date, String rental, String color, String comment) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.rental = rental;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] data(){
        return new Object[][]{
                {"Иван", "Иванов", "Королева 15", "Сокольники", "12345678901", "01.01.2025", "сутки", "black", "Привет"},
                {"Петр", "Петров", "Королева 67б", "Чистые пруды", "10987654321", "10.10.2025", "двое суток", "grey", "Пока"},
        };
    }

    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(BROWSER);
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void createNewOrderButtonAtTheTop(){
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickHeaderCreateButton();

        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.firstPageCustomerInfo(name, lastname, address, station, phone);
        orderPage.clickNextButton();
        orderPage.secondPageCustomerInfo(date, rental, color, comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmationButton();
        boolean orderAcceptedIsDisplayed = orderPage.orderAccepted();
        assertTrue(orderAcceptedIsDisplayed);

    }

   @Test
    public void createNewOrderButtonAtTheBottom(){
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickHomeCreateButton();

        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.firstPageCustomerInfo(name, lastname, address, station, phone);
        orderPage.clickNextButton();
        orderPage.secondPageCustomerInfo(date, rental, color, comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmationButton();
        boolean orderAcceptedIsDisplayed = orderPage.orderAccepted();
        assertTrue(orderAcceptedIsDisplayed);
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}
