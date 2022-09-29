package com.upc.pe.backenderentcar.test.step.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.pe.backenderentcar.car.resource.CarResource;
import com.upc.pe.backenderentcar.car.resource.CreateCarResource;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.opentest4j.AssertionFailedError;
import static org.assertj.core.api.Assertions.assertThat;

public class CarAddingStepDefinition {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;

    private String endPointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The Endpoint {string} is available for cars")
    public void theEndpointIsAvailableForCars(String endPointPath) {
        this.endPointPath = String.format("http://localhost:%d/api/v1/cars", randomServerPort);
    }


    @When("A Car Request is sent with values {string}, {string}, {int}, {string}, {int}, {int}, {string}, {int}, {string}, {int}, {int}, {string}, {string}, {string}")
    public void aCarRequestIsSentWithValuesTrue(String address, String brand, int year, String model, int mileage, int seating, String manual, int carValueInDollars, String extraInformation, int rate, int rentAmountDay, String imagePath, String category, String mechanicConditions) {
        CreateCarResource resource = new CreateCarResource()
                .withAddress(address)
                .withBrand(brand)
                .withYear(year)
                .withModel(model)
                .withMileage(mileage)
                .withSeating(seating)
                .withManual(manual)
                .withCarValueInDollars(carValueInDollars)
                .withExtraInformation(extraInformation)
                .withRate(rate)
                .withRentAmountDay(rentAmountDay)
                .withImagePath(imagePath)
                .withCategory(category)
                .withMechanicConditions(mechanicConditions);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateCarResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }
    @Then("A Response with Status {int} is received for the car")
    public void aResponseWithStatusIsReceivedForTheCar(int expectedStatus) {
        int actualStatus = responseEntity.getStatusCodeValue();
        assertThat(actualStatus).isNotEqualTo(expectedStatus);
    }

    @And("A Car Resource with values {string}, {string}, {int}, {string}, {int}, {int}, {string}, {int}, {string}, {int}, {int}, {string}, {string}, {string}")
    public void aCarResourceWithValuesTrue(String address, String brand, int year, String model, int mileage, int seating, String manual, int carValueInDollars, String extraInformation, int rate, int rentAmountDay, String imagePath, String category, String mechanicConditions) {
        CarResource expectedResource = new CarResource()
                .withAddress(address)
                .withBrand(brand)
                .withYear(year)
                .withModel(model)
                .withMileage(mileage)
                .withSeating(seating)
                .withManual(manual)
                .withCarValueInDollars(carValueInDollars)
                .withExtraInformation(extraInformation)
                .withRate(rate)
                .withRentAmountDay(rentAmountDay)
                .withImagePath(imagePath)
                .withCategory(category)
                .withMechanicConditions(mechanicConditions);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        CarResource actualResource;
        try {
            actualResource = mapper.readValue(value, CarResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new CarResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison().isEqualTo(actualResource);
    }
}
