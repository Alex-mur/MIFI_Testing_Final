package fun.justdevelops.driver;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;
import java.time.Duration;

public class TestDriverFactory {
    public static WebDriver createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public static AndroidDriver createAndroidDriver() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel7pro");
        options.setAppPackage("org.wikipedia");
        options.setAppActivity("org.wikipedia.main.MainActivity");
        options.setNoReset(false);
        return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }
}
