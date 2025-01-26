package com.moneytransfer;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Base {
    protected static WebDriver driver;

    public static void initializeDriver() {
        // ChromeDriver için path'i belirtiyoruz
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        // Tarayıcı ayarları (opsiyonel)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        // Driver başlatma
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}