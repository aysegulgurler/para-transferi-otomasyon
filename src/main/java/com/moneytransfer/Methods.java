package com.moneytransfer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Methods {
    private WebDriver driver;

    public Methods(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void sendKeys(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    public void waitBySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000); // Saniyeyi milisaniyeye çevir
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ComboBox'tan rastgele seçim yapma
    public void selectRandomFromComboBox(By locator) {
        WebElement comboBox = driver.findElement(locator);
        Select select = new Select(comboBox);

        // Tüm seçenekleri al
        List<WebElement> options = select.getOptions();

        // Seçenekler arasında rastgele seçim yap
        Random rand = new Random();
        int randomIndex = rand.nextInt(options.size());

        // Seçimi yap
        select.selectByIndex(randomIndex);
    }

    public void clickAndTypeWithJS(By locator, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // JavaScript ile tıklama
            js.executeScript("arguments[0].click();", element);

            // JavaScript ile yazma
            js.executeScript("arguments[0].value=arguments[1];", element, text);

            // Olası bir 'onchange' veya 'oninput' olayını tetikle
            js.executeScript("arguments[0].dispatchEvent(new Event('change'));", element);

        } catch (Exception e) {
            System.err.println("Error in clickAndTypeWithJS method: " + e.getMessage());
        }
    }

    public void scrollAndClick(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Elemente kaydır
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

            // Elemente tıkla
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.err.println("Error in scrollAndClick method: " + e.getMessage());
        }
    }

}


