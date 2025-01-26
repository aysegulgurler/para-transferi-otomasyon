package com.moneytransfer.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class Methods {
    private WebDriver driver;

    public Methods(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeys(By locator, String value) {
        WebElement element = driver.findElement(locator);
        element.sendKeys(value);
    }

    public void clickElement(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public void waitBySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectRandomFromComboBox(By locator) {
        WebElement comboBox = driver.findElement(locator);
        Select select = new Select(comboBox);
        List<WebElement> options = select.getOptions();
        Random rand = new Random();
        int randomIndex = rand.nextInt(options.size());
        select.selectByIndex(randomIndex);
    }

    public String getText(By locator) {
        WebElement element = driver.findElement(locator);
        return element.getText();
    }
}
