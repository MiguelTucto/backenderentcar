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
        webDriver.get("http://127.0.0.1:5173/register");
    }

    @And("Complete his personal information")
    public void completeHisPersonalInformation() {
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div[2]/form/div[1]/input[1]")).sendKeys("Angel");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div[2]/form/div[1]/input[2]")).sendKeys("Herrera");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div[2]/form/div[1]/input[3]")).sendKeys("Arrendatario");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div[2]/form/div[1]/input[4]")).sendKeys("https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        webDriver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("978451245");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div[2]/form/div[2]/button[2]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div[2]/form/div[1]/input[1]")).sendKeys("angel");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div[2]/form/div[1]/input[2]")).sendKeys("angel");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div/div/div[2]/form/div[2]/button[2]")).click();

    }

    @When("Click the button Register")
    public void clickTheButtonRegister() {
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[2]/button[2]")).click();
    }

    @Then("The System will show a message")
    public void theSystemWillShowAMessage() {
        webDriver.get("http://127.0.0.1:5173/");
    }
}
