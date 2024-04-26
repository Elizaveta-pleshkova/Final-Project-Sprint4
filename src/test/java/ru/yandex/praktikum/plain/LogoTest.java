package ru.yandex.praktikum.plain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;

import static org.junit.Assert.assertTrue;

public class LogoTest {
    private WebDriver webDriver;
    private static final String BROWSER = "firefox";

    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(BROWSER);

        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void scooterHyperlinkLeadsToTheScooterHomePage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickHeaderCreateButton();

        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.clickHyperlinkedScooter();
        boolean homePageScooterIsDisplayed = orderPage.homePageScooterOpen();
        assertTrue(homePageScooterIsDisplayed);

    }

    @Test
    public void yandexHyperlinkLeadsToTheYandexHomePage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickHyperlinkedYandex();
        boolean homePageYandexIsDisplayed = mainPage.homePageYandexOpen();
        assertTrue(homePageYandexIsDisplayed);
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}
