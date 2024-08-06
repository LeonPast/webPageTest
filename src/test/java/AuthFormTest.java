import org.example.BaseTest;
import org.example.LoginPage;
import org.example.SurveyPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthFormTest extends BaseTest {

    @Test
    public void testLoginPage() {
        LoginPage loginPage = new LoginPage(driver);

        assertTrue(new LoginPage(driver).isLoginPageDisplayed());
    }
    @Test
    public void testLoginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("test@protei.ru");
        loginPage.enterPassword("test");
        loginPage.clickLoginButton();

        assertTrue(new SurveyPage(driver).isSurveyPageDisplayed());
    }

    @Test
    public void testLoginFailureWithInvalidEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("wrong@protei.ru");
        loginPage.enterPassword("test");
        loginPage.clickLoginButton();

        assertTrue(loginPage.isErrorDisplayed());
    }

    @Test
    public void testLoginFailureWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("test@protei.ru");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLoginButton();

        assertTrue(loginPage.isErrorDisplayed());
    }

    @Test
    public void testLoginFailureWithInvalidEmailFormat() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("testprotei.ru");
        loginPage.enterPassword("test");
        loginPage.clickLoginButton();

        WebElement emailFormatError = driver.findElement(By.xpath("//div[@id='emailFormatError']"));
        assertTrue(emailFormatError.isDisplayed());
    }

    @Test
    public void testFormSubmission() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("test@protei.ru");
        loginPage.enterPassword("test");
        loginPage.clickLoginButton();

        SurveyPage surveyPage = new SurveyPage(driver);
        surveyPage.enterEmail("user@example.com");
        surveyPage.enterName("Test User");
        surveyPage.selectGender("Мужской");
        surveyPage.selectCheckboxOption("dataCheck11");
        surveyPage.selectRadioOption("dataSelect21");
        surveyPage.clickSubmitButton();

        assertTrue(surveyPage.isDataAdded("user@example.com", "Test User", "Мужской", "1.1", "2.1"));
    }

    @Test
    public void testFormSubmissionWithInvalidEmailFormat() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("test@protei.ru");
        loginPage.enterPassword("test");
        loginPage.clickLoginButton();

        SurveyPage surveyPage = new SurveyPage(driver);
        surveyPage.enterEmail("userexample.com");
        surveyPage.enterName("Test User");
        surveyPage.selectGender("Мужской");
        surveyPage.selectCheckboxOption("dataCheck11");
        surveyPage.selectRadioOption("dataSelect21");
        surveyPage.clickSubmitButton();

        WebElement emailFormatError = driver.findElement(By.xpath("//div[@id='emailFormatError']"));
        assertTrue(emailFormatError.isDisplayed());
    }

    @Test
    public void testFormSubmissionWithBlankName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("test@protei.ru");
        loginPage.enterPassword("test");
        loginPage.clickLoginButton();

        SurveyPage surveyPage = new SurveyPage(driver);
        surveyPage.enterEmail("user@example.com");
        surveyPage.enterName("");
        surveyPage.selectGender("Мужской");
        surveyPage.selectCheckboxOption("dataCheck11");
        surveyPage.selectRadioOption("dataSelect21");
        surveyPage.clickSubmitButton();

        WebElement blankNameError = driver.findElement(By.xpath("//div[@id='blankNameError']"));

        assertTrue(blankNameError.isDisplayed());
    }

    @Test
    public void testFormSubmissionWithoutCheckboxesAndRadioButtons() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("test@protei.ru");
        loginPage.enterPassword("test");
        loginPage.clickLoginButton();

        SurveyPage surveyPage = new SurveyPage(driver);
        surveyPage.enterEmail("user@example.com");
        surveyPage.enterName("Test User");
        surveyPage.selectGender("Мужской");
        surveyPage.clickSubmitButton();

        assertTrue(surveyPage.isDataAdded("user@example.com", "Test User", "Мужской", "Нет", ""));
    }
}
