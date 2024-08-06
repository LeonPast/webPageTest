package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(By.xpath("//input[@id='loginEmail']"));
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='loginPassword']"));
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.xpath("//button[@id='authButton']"));
        loginButton.click();
    }

    public boolean isErrorDisplayed() {
        WebElement alert = driver.findElement(By.xpath("//div[@id='invalidEmailPassword']"));
        return alert.isDisplayed();
    }

    public boolean isLoginPageDisplayed() {
        WebElement loginForm = driver.findElement(By.xpath("//div[@id='authPage']"));
        return loginForm.isDisplayed();
    }
}
