package common;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {
    private final static String selenideProperties = "selenide.properties";

    @BeforeAll
    static void setupClass() {
        Properties props = new Properties();
        try (InputStream inputStream = BaseTest.class
                .getClassLoader()
                .getResourceAsStream(selenideProperties)) {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!props.isEmpty()) {
            for (Object propObj : props.keySet()) {
                String prop = String.valueOf(propObj);

                if (!System.getProperties().containsKey(prop)) {
                    System.setProperty(prop, props.getProperty(prop));
                }
            }
        }
        //todo вкл селеноид(не могу проверить у себя на компе)

//        Configuration.remote = "http://localhost:4444/wd/hub";
//        Configuration.reportsFolder = "target/surefire-reports";
//        Configuration.downloadsFolder = "target/downloads";
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("enableVNC", true);
//        capabilities.setCapability("enableVideo", true);
//        capabilities.setCapability("enableLog", true);
//        Configuration.browserCapabilities = capabilities;
    }

    @AfterAll
    static void cleanupClass() {
        if (WebDriverRunner.hasWebDriverStarted()) WebDriverRunner.closeWebDriver();
    }

    @BeforeEach
    protected void setupTest() {
    }

    @AfterEach
    protected void cleanupTest() {
        WebDriverRunner.closeWindow();
    }
}
