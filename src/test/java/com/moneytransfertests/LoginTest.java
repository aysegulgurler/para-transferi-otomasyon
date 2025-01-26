package com.moneytransfertests;

import com.moneytransfer.main.Base;
import com.moneytransfer.main.Methods;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends Base {
    private static Methods methods;
    private static ExtentReports extentReports;
    private static ExtentTest test;

    @BeforeAll
    public static void setUp() {
        // Extent Reports setup
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-report.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        // Tarayıcıyı başlat ve login sayfasına git
        initializeDriver();
        driver.get("https://catchylabs-webclient.testinium.com/signIn");
        methods = new Methods(driver);

        // Start a new test in the report
        test = extentReports.createTest("Valid Login Test", "Test to validate successful login.");
    }

    @AfterAll
    public static void tearDown() {
        // Test reportu bitir
        extentReports.flush();

        // Tarayıcıyı kapat
        quitDriver();
    }

    @Test
    public void testValidLogin() {
        // Test Data
        String username = "aysegul.gurler@testinium.com";
        String password = "Ayse9gul.";

        try {
            // Kullanıcı adı alanına veri gir
            methods.sendKeys(By.xpath("(//input[@value])[1]"), username);
            test.info("Kullanıcı adı girildi: " + username);

            // Şifre alanına veri gir
            methods.sendKeys(By.xpath("(//input[@value])[2]"), password);
            test.info("Şifre girildi: " + password);

            // Login butonuna tıkla
            methods.clickElement(By.cssSelector(".css-146c3p1.r-jwli3a.r-1b43r93"));
            test.info("Login butonuna tıklandı.");

            methods.waitBySeconds(2);

            // Başarılı giriş kontrolü: Ana sayfanın yüklendiğini kontrol et
            boolean isDashboardLoaded = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-146c3p1.r-jwli3a.r-1b43r93"))) != null;

            assertTrue(isDashboardLoaded, "Kullanıcı giriş yaptı ve ana sayfa yüklendi.");
            test.pass("Başarılı giriş yapıldı ve ana sayfa yüklendi.");
        } catch (Exception e) {
            // Hata durumunda raporlama
            test.fail("Test sırasında bir hata oluştu: " + e.getMessage());
        }

        methods.waitBySeconds(2);
    }
}
