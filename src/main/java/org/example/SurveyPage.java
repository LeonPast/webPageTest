package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SurveyPage {
    private final WebDriver driver;

    public SurveyPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(By.xpath("//input[@id='dataEmail']"));
        emailInput.sendKeys(email);
    }

    public void enterName(String name) {
        WebElement nameInput = driver.findElement(By.xpath("//input[@id='dataName']"));
        nameInput.sendKeys(name);
    }

    public void selectGender(String gender) {
        WebElement genderSelect = driver.findElement(By.xpath("//select[@id='dataGender']"));
        genderSelect.sendKeys(gender);
    }

    public void selectCheckboxOption(String optionId) {
        WebElement checkbox = driver.findElement(By.xpath("//input[@id='" + optionId + "']"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void selectRadioOption(String optionId) {
        WebElement radio = driver.findElement(By.xpath("//input[@id='" + optionId + "']"));
        if (!radio.isSelected()) {
            radio.click();
        }
    }

    public void clickSubmitButton() {
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='dataSend']"));
        submitButton.click();
    }

    public boolean isSurveyPageDisplayed() {
        WebElement surveyForm = driver.findElement(By.xpath("//div[@id='inputsPage']"));
        return surveyForm.isDisplayed();
    }

    public boolean isDataAdded(String email, String name, String gender, String option1, String option2) {
        WebElement dataTable = driver.findElement(By.xpath("//table[@id='dataTable']"));
        String tableText = dataTable.getText();
        return tableText.contains(email) && tableText.contains(name) && tableText.contains(gender) && tableText.contains(option1) && tableText.contains(option2);
    }
}
