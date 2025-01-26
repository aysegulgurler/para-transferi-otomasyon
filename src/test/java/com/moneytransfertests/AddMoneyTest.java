package com.moneytransfertests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import com.moneytransfer.Methods;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddMoneyTest extends LoginTest { // LoginTest'ten miras alıyoruz
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
    public void testAddMoney() {
        // "Add Money" butonuna tıkla
        methods.clickElement(By.cssSelector(".css-146c3p1.r-jwli3a.r-1b43r93"));
        methods.waitBySeconds(2);

        methods.clickElement(By.xpath("(//div[@class='css-146c3p1 r-jwli3a r-1b43r93'])[3]"));
        methods.waitBySeconds(2);


        // Ödeme bilgilerini gir
        methods.sendKeys(By.xpath("(//div[@class='css-175oi2r r-13qz1uu']//input)[1]"), "1234 1234 1234 1234");
        methods.waitBySeconds(2);
        methods.clickElement(By.xpath("(//div[@class='css-175oi2r r-13qz1uu']//input)[2]"));
        methods.waitBySeconds(2);
        methods.clickAndTypeWithJS(By.xpath("(//div[@class='css-175oi2r r-13qz1uu']//input)[2]"), "Ayşegül Test");
        methods.waitBySeconds(2);
        methods.clickAndTypeWithJS(By.xpath("(//div[@class='css-175oi2r r-13qz1uu']//input)[3]"), "10/26");
        methods.waitBySeconds(2);
        methods.clickAndTypeWithJS(By.xpath("(//div[@class='css-175oi2r r-13qz1uu']//input)[4]"), "110");
        methods.waitBySeconds(2);
        methods.clickAndTypeWithJS(By.xpath("(//div[@class='css-175oi2r r-13qz1uu']//input)[5]"), "1000");
        methods.waitBySeconds(2);


        // "Add" butonuna tıkla
        methods.scrollAndClick(By.xpath("//div[@class='css-175oi2r r-1i6wzkk r-lrvibr r-1loqt21 r-1otgn73 r-1awozwy r-169ebfh r-z2wwpe r-h3s6tt r-1777fci r-tsynxw r-13qz1uu']"));
        methods.waitBySeconds(10);

        // Bakiye bilgisini al ve kontrol et
        WebElement balanceElement = driver.findElement(By.xpath("(//div[@class='css-146c3p1 r-1ozpqpt r-yv33h5 r-1b43r93'])[3]"));
        String updatedBalance = balanceElement.getText();
        methods.waitBySeconds(3);


    }
}
