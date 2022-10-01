package com.upc.pe.backenderentcar.test.step.user;

import com.upc.pe.backenderentcar.shared.driver.MyWebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class US04StepDefinition {
    public static WebDriver webDriver;
    public US04StepDefinition() { webDriver = MyWebDriver.getWebDriver(); }
    @Given("The User is in Register Section")
    public void theUserIsInRegisterSection(){
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/form/div/button[2]")).click();

    }

    @And("Complete his personal information")
    public void completeHisPersonalInformation() {
        webDriver.findElement(By.id("registerName")).sendKeys("Romina");
        webDriver.findElement(By.id("registerLastName")).sendKeys("Acuña");
        webDriver.findElement(By.id("registerTypeOfUser")).sendKeys("Arrendador");
        webDriver.findElement(By.id("registerImage")).sendKeys("image.url");
        webDriver.findElement(By.id("registerPhone")).sendKeys("978457845");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div[2]/form/div[2]/button[2]")).click();
    }

    @When("Click the button Register")
    public void clickTheButtonRegister() {
        webDriver.findElement(By.id("registerEmail")).sendKeys("romi");
        webDriver.findElement(By.id("registerPassword")).sendKeys("romi");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div[2]/form/div[2]/button[2]")).click();
    }

    @Then("The System will show a message")
    public void theSystemWillShowAMessage() {
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[2]/button[2]")).click();
    }
}
