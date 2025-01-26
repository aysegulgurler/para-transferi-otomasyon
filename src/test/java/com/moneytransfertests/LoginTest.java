package com.moneytransfertests;

import com.moneytransfer.Base;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import com.moneytransfer.Methods;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends Base {
    private static Methods methods;

    @BeforeAll
    public static void setUp() {
        // Tarayıcıyı başlat ve login sayfasına git
        initializeDriver();
        driver.get("https://catchylabs-webclient.testinium.com/signIn");
        methods = new Methods(driver);
    }

    @AfterAll
    public static void tearDown() {
        // Tarayıcıyı kapat
        quitDriver();
    }

    @Test
    public void testValidLogin() {
        // Test Data
        String username = "aysegul.gurler@testinium.com";
        String password = "Ayse9gul.";

        // Kullanıcı adı alanına veri gir
        methods.sendKeys(By.xpath("(//input[@value])[1]"), username);

        // Şifre alanına veri gir
        methods.sendKeys(By.xpath("(//input[@value])[2]"), password);


        // Login butonuna tıkla
        methods.clickElement(By.cssSelector(".css-146c3p1.r-jwli3a.r-1b43r93"));
        methods.waitBySeconds(2);

        // Başarılı giriş kontrolü: Ana sayfanın yüklendiğini kontrol et
        boolean isDashboardLoaded = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-146c3p1.r-jwli3a.r-1b43r93"))) != null;
        assertTrue(isDashboardLoaded, "Kullanıcı giriş yaptı ve ana sayfa yüklendi.");

        methods.waitBySeconds(2);
    }
}
