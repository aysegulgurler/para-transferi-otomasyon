package com.moneytransfertests;

import com.moneytransfer.main.Base;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.moneytransfer.main.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateAccountNameTest extends LoginTest { // Login işlemini miras alıyoruz
    private static Methods methods;
    private static ExtentReports extentReports;
    private static ExtentTest test;

    @BeforeAll
    public static void login() {
        // ExtentReports setup
        extentReports = new ExtentReports();
        test = extentReports.createTest("Update Account Name Test", "Test to update account name.");
        test.info("Test başlatıldı.");

        // Login işlemini testValidLogin metoduyla yap
        TransferMoneyTest transferMoneyTest = new TransferMoneyTest();
        transferMoneyTest.testValidLogin();
        methods = new Methods(driver);
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
    public void testUpdateAccountName() {
        // Yeni hesap adı
        String newAccountName = "My Updated Account";

        try {
            // Hesap ayarları menüsüne tıkla
            methods.clickElement(By.cssSelector(".css-146c3p1.r-jwli3a.r-1b43r93"));
            test.info("'Hesap Ayarları' menüsüne tıklandı.");
            methods.waitBySeconds(2);

            // "Edit Account" butonuna tıkla
            methods.clickElement(By.xpath("(//div[@class='css-146c3p1 r-jwli3a r-1b43r93'])[4]"));
            test.info("'Edit Account' butonuna tıklandı.");
            methods.waitBySeconds(2);

            // "Account Name" alanına yeni hesap adını gir
            WebElement accountNameField = driver.findElement(By.xpath("//div[@class='css-175oi2r r-13qz1uu']//input"));
            accountNameField.clear(); // Eski adı temizle
            accountNameField.sendKeys(newAccountName);
            test.info("Yeni hesap adı girildi: " + newAccountName);
            methods.waitBySeconds(2);

            // "Save" butonuna tıkla
            methods.clickElement(By.xpath("//div[@class='css-175oi2r r-1i6wzkk r-lrvibr r-1loqt21 r-1otgn73 r-1awozwy r-169ebfh r-z2wwpe r-h3s6tt r-1777fci r-tsynxw r-13qz1uu']"));
            test.info("'Save' butonuna tıklandı.");
            methods.waitBySeconds(2);

            // Hesap adının güncellendiğini kontrol et
            WebElement updatedAccountNameField = driver.findElement(By.xpath("(//div[@class='css-146c3p1 r-1ozpqpt r-yv33h5 r-1b43r93'])[1]"));
            String updatedAccountName = updatedAccountNameField.getText();
            assertEquals(newAccountName, updatedAccountName, "Hesap adı güncellenemedi veya yanlış güncellendi.");
            test.pass("Hesap adı başarıyla güncellendi.");
        } catch (Exception e) {
            // Hata durumunda raporlama
            test.fail("Test sırasında bir hata oluştu: " + e.getMessage());
        }
    }
}
