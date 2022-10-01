package com.upc.pe.backenderentcar.shared.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyWebDriver {
    public static WebDriver webDriver;
    private MyWebDriver(){ }
    public static WebDriver getWebDriver() {
            if (webDriver == null) {
                String pathDriver = System.getProperty("user.dir") + "\\driver\\chromedriver_100_60.exe";
                System.setProperty("webDriver.chrome.driver", pathDriver);
                webDriver = new ChromeDriver();
                webDriver.get("http://127.0.0.1:5173/");
                webDriver.manage().window().maximize();
            }
        return webDriver;
    }
}
