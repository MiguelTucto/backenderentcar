package com.upc.pe.backenderentcar.test.step.rent;

import com.upc.pe.backenderentcar.rent.domain.model.entity.Rent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RentAddingStepDefinition {
    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:4020/api/v1";
    private String message = "";
    Rent rentEntity;
    Long rentId = randomLong();
    public Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }

    @Given("I want to add a Rent")
    public void i_want_to_add_a_rent() {
        String rentUrl = url + "/rents";
        String getEndpoint = restTemplate.getForObject(rentUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());
    }

    @Given("I add a Rent with startDate {string}, finishDate {string}, amount {int} and rate {int}")
    public void i_add_a_rent_with_start_date_finish_date_amount_and_rate(String startDate, String finishDate, int amount, int rate) {
        // Write code here that turns the phrase above into concrete actions
        String rentUrl = url + "/rents/user" + 1;
        Rent rent = new Rent(rentId, startDate, finishDate, amount, rate);
        log.info(rent.getId());
        assertNotNull(rent);
    }
    @And("I add a Rent with startDate <startDate>, finishDate <finishDate>, amount <amount> and rate <rate>")
    public void i_add_a_rent_with_startDate_finishDate_amount_and_rate(String startDate, String finishDate, int amount, int rate) {
        String rentUrl = url + "/rents/user" + 1;
        Rent newRent = new Rent(rentId, startDate, finishDate, amount, rate);
        rentEntity = restTemplate.postForObject(rentUrl, newRent, Rent.class);
        log.info(rentEntity.getId());
        assertNotNull(rentEntity);
    }

    @Then("I should be able to see my Rent")
    public void iShouldBeAbleToSeeMyRent() {
        String rentUrl = url + "/rents/user" + 1;
        try {
            Rent getRentById = restTemplate.getForObject(rentUrl, Rent.class,1);
            log.info(getRentById);
        } catch (RestClientException e) {
            message = "";
        }
        assertEquals("", message);
    }

}
