package com.upc.pe.backenderentcar.test.step.car;

import com.upc.pe.backenderentcar.shared.driver.MyWebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class US09StepDefinition {
    public static WebDriver webDriver;

    public US09StepDefinition() { webDriver = MyWebDriver.getWebDriver(); }

    @Given("User in main menu")
    public void userInMainMenu() {
        webDriver.findElement(By.id("loginEmail")).sendKeys("2cto");
        webDriver.findElement(By.id("loginPassword")).sendKeys("2cto");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/form/div/button[1]")).click();
    }

    @When("Complete with his personal car information")
    public void completeWithHisPersonalCarInformation() {

    }

    @Then("It will complete")
    public void itWillComplete() {
    }
}
