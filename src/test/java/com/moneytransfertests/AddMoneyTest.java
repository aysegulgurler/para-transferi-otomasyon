package com.moneytransfertests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import com.moneytransfer.main.Methods;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddMoneyTest extends LoginTest { // LoginTest'ten miras alıyoruz
    private static Methods methods;
    private static ExtentTest test;
    private static ExtentReports extentReports;

    @BeforeAll
    public static void login() {
        // Login işlemini testValidLogin metoduyla yap
        TransferMoneyTest transferMoneyTest = new TransferMoneyTest();
        transferMoneyTest.testValidLogin();
        methods = new Methods(driver);

        // Test başlatma ve raporlama
        test = extentReports.createTest("Add Money Test", "Test to validate add money functionality.");
        test.info("Test başlatıldı.");
    }

    @AfterAll
    public static void tearDown() {
        // Test raporunun bitirilmesi
        test.info("Test tamamlandı.");
        extentReports.flush();

        // Tarayıcıyı kapat
        quitDriver();
    }

    @Test
    public void testAddMoney() {
        try {
            // "Add Money" butonuna tıkla
            methods.clickElement(By.cssSelector(".css-146c3p1.r-jwli3a.r-1b43r93"));
            test.info("'Add Money' butonuna tıklandı.");
            methods.waitBySeconds(2);

            methods.clickElement(By.xpath("(//div[@class='css-146c3p1 r-jwli3a r-1b43r93'])[3]"));
            test.info("'Add Money' seçeneği tıklandı.");
            methods.waitBySeconds(2);

            // Ödeme bilgilerini gir
            methods.sendKeys(By.xpath("(//div[@class='css-175oi2r r-13qz1uu']//input)[1]"), "1234 1234 1234 1234");
            test.info("Kart numarası girildi.");
            methods.waitBySeconds(2);
            methods.sendKeys(By.xpath("(//div[@class='css-175oi2r r-13qz1uu']//input)[2]"), "Ayşegül");
            test.info("Kart sahibi adı girildi.");
            methods.waitBySeconds(2);
            methods.sendKeys(By.xpath("(//div[@class='css-175oi2r r-13qz1uu']//input)[3]"), "1026");
            test.info("Son kullanma tarihi girildi.");
            methods.waitBySeconds(2);
            methods.sendKeys(By.xpath("(//div[@class='css-175oi2r r-13qz1uu']//input)[4]"), "110");
            test.info("CVV kodu girildi.");
            methods.waitBySeconds(2);

            // "Add" butonuna tıkla
            methods.clickElement(By.xpath("(//div[@class='css-175oi2r r-1i6wzkk r-lrvibr r-1loqt21 r-1otgn73 r-1awozwy r-169ebfh r-z2wwpe r-h3s6tt r-1777fci r-tsynxw r-13qz1uu']"));
            test.info("'Add' butonuna tıklandı.");
            methods.waitBySeconds(2);

            // Bakiye bilgisini al ve kontrol et
            WebElement balanceElement = driver.findElement(By.xpath("(//div[@class='css-146c3p1 r-1ozpqpt r-yv33h5 r-1b43r93'])[3]"));
            String updatedBalance = balanceElement.getText();

            // Bakiye bilgisini doğrula
            assertTrue(updatedBalance.contains("Updated Balance"), "Bakiye güncellenmedi!");
            test.pass("Bakiye başarıyla güncellendi: " + updatedBalance);
        } catch (Exception e) {
            // Hata durumunda raporlama
            test.fail("Test sırasında bir hata oluştu: " + e.getMessage());
        }

        methods.waitBySeconds(2);
    }
}