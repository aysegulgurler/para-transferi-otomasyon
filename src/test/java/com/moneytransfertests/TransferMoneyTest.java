package com.moneytransfertests;

import com.moneytransfer.main.Base;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import com.moneytransfer.main.Methods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import static com.moneytransfer.main.Base.initializeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferMoneyTest extends LoginTest {
    private static Methods methods;
    private static ExtentReports extentReports;
    private static ExtentTest test;

    @BeforeAll
    public static void login() {
        // ExtentReports setup
        extentReports = new ExtentReports();
        test = extentReports.createTest("Transfer Money Test", "Test to validate money transfer functionality.");
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
    public void testMoneyTransfer() {
        try {
            // "Transfer Money" butonuna tıkla
            methods.clickElement(By.cssSelector(".css-146c3p1.r-jwli3a.r-1b43r93"));
            test.info("'Transfer Money' butonuna tıklandı.");
            methods.waitBySeconds(2);

            // İkinci butona tıkla
            methods.clickElement(By.xpath("(//div[@class='css-146c3p1 r-jwli3a r-1b43r93'])[2]"));
            test.info("İkinci transfer butonuna tıklandı.");
            methods.waitBySeconds(2);

            // Gönderen hesap, alıcı hesap ve transfer miktarını gir
            // ComboBox'tan rastgele bir seçim yap
            By comboBoxLocator2 = By.xpath("(//div[@class='css-175oi2r r-1777fci'])[1]//select");
            methods.selectRandomFromComboBox(comboBoxLocator2);
            test.info("Gönderen hesap seçildi.");

            // ComboBox'tan rastgele bir seçim yap
            By comboBoxLocator = By.xpath("(//div[@class='css-175oi2r r-1777fci'])[2]//select");
            methods.selectRandomFromComboBox(comboBoxLocator);
            test.info("Alıcı hesap seçildi.");

            // Transfer miktarını gir
            methods.waitBySeconds(2);
            methods.sendKeys(By.cssSelector(".css-11aywtz.r-6taxm2.r-1eh6qqt.r-z2wwpe.r-rs99b7.r-h3s6tt.r-1qhn6m8"), "350");
            test.info("Transfer miktarı girildi: 350");
            methods.waitBySeconds(2);

            // "Send" butonuna tıkla
            methods.clickElement(By.xpath("(//div[@class='css-175oi2r r-1i6wzkk r-lrvibr r-1loqt21 r-1otgn73 r-1awozwy r-169ebfh r-z2wwpe r-h3s6tt r-1777fci r-tsynxw r-13qz1uu'])"));
            test.info("'Send' butonuna tıklandı.");
            methods.waitBySeconds(3);

            // Transfer işleminin başarılı olduğunu ve miktarın doğruluğunu kontrol et
            String confirmationMessage = methods.getText(By.xpath("(//div[@class='css-175oi2r r-18u37iz r-13qz1uu'])[4]//div//div"));
            boolean isTransferSuccessful = confirmationMessage.contains("Transfer successful") && confirmationMessage.contains("350");

            assertTrue(isTransferSuccessful, "Transfer işlemi başarısız veya miktar yanlış.");
            test.pass("Transfer işlemi başarılı ve doğru miktar girildi.");
        } catch (Exception e) {
            // Hata durumunda raporlama
            test.fail("Test sırasında bir hata oluştu: " + e.getMessage());
        }

        methods.waitBySeconds(2);
    }
}
