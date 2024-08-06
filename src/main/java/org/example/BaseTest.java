package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class BaseTest {
    protected ChromeDriver driver;

    @BeforeEach
    public void setUp() {
        String chromeDriverPath = getPathFromResource("chromedriver.exe");
        String htmlPagePath = getPathFromResource("qa-test.html");

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("chrome.switches", "--disable-extensions");
        options.addArguments("--disable-save-password");
        options.addArguments("disable-infobars");

        driver = new ChromeDriver(options);
        driver.get("file:///" + htmlPagePath);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String getPathFromResource(String resource) {
        try {
            return Paths.get(getClass().getClassLoader().getResource(resource).toURI()).toAbsolutePath().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Resource not found: " + resource, e);
        }
    }
}