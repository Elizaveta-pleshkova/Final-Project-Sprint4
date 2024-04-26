package ru.yandex.praktikum.plain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.MainPage;

import static org.junit.Assert.assertTrue;

public class OrderTest {
    private WebDriver webDriver;
    private static final String BROWSER = "firefox";


    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(BROWSER);
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void NotFoundOrder(){
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderStatus();
        mainPage.inputOrderNumber("84720384");
        mainPage.clickGoButton();
        boolean imgNotFoundIsDisplayed = mainPage.imgNotFoundIsDisplayed();
        assertTrue(imgNotFoundIsDisplayed);
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}
