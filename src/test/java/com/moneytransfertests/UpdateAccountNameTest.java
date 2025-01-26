package com.moneytransfertests;

import com.moneytransfer.Base;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.moneytransfer.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateAccountNameTest extends LoginTest { // Login işlemini miras alıyoruz
    private static Methods methods;

    @BeforeAll
    public static void login() {
        // Login işlemini testValidLogin metoduyla yap
        TransferMoneyTest transferMoneyTest = new TransferMoneyTest();
        transferMoneyTest.testValidLogin();
        methods = new Methods(driver);
    }

    @AfterAll
    public static void tearDown() {
        // Tarayıcıyı kapat
        quitDriver();
    }

    @Test
    public void testUpdateAccountName() {
        // Yeni hesap adı
        String newAccountName = "My Updated Account";

        methods.clickElement(By.cssSelector(".css-146c3p1.r-jwli3a.r-1b43r93"));
        methods.waitBySeconds(2);
        // "Edit Account" butonuna tıkla
        methods.clickElement(By.xpath("(//div[@class='css-146c3p1 r-jwli3a r-1b43r93'])[4]"));
        methods.waitBySeconds(2);

        // "Account Name" alanına yeni hesap adını gir
        WebElement accountNameField = driver.findElement(By.xpath("//div[@class='css-175oi2r r-13qz1uu']//input"));
        accountNameField.clear(); // Eski adı temizle
        accountNameField.sendKeys(newAccountName);
        methods.waitBySeconds(2);

        // "Save" butonuna tıkla
        methods.clickElement(By.xpath("//div[@class='css-175oi2r r-1i6wzkk r-lrvibr r-1loqt21 r-1otgn73 r-1awozwy r-169ebfh r-z2wwpe r-h3s6tt r-1777fci r-tsynxw r-13qz1uu']"));
        methods.waitBySeconds(2);

        // Hesap adının güncellendiğini kontrol et
        WebElement updatedAccountNameField = driver.findElement(By.xpath("(//div[@class='css-146c3p1 r-1ozpqpt r-yv33h5 r-1b43r93'])[1]"));
        String updatedAccountName = updatedAccountNameField.getText();
        assertEquals(newAccountName, updatedAccountName, "Hesap adı güncellenemedi veya yanlış güncellendi.");
    }
}