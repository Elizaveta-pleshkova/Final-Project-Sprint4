package ru.yandex.praktikum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static WebDriver getWebDriver(String browserType){
        if (browserType.equalsIgnoreCase("firefox")){
            return new FirefoxDriver();
        }
        else{
            return new ChromeDriver();
        }
    }
}
