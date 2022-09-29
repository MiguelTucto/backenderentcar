package com.upc.pe.backenderentcar.test.step.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.pe.backenderentcar.user.resource.CreateUserResource;
import com.upc.pe.backenderentcar.user.resource.LoginUserResource;
import com.upc.pe.backenderentcar.user.resource.UserResource;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserLoggingStepDefinition {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int randomServerPort;
    private String endPointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The Endpoint {string} is available for logging users")
    public void theEndpointIsAvailableForLoggingUsers(String endPointPath) {
        this.endPointPath = String.format("http://localhost:%d/api/v1/users/login", randomServerPort);
    }

    @When("A User request is sent with values {string}, {string}")
    public void aUserRequestIsSentWithValues(String email, String password) {
        LoginUserResource resource = new LoginUserResource()
                .withEmail(email)
                .withPassword(password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginUserResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received for logging user")
    public void aResponseWithStatusIsReceivedForLoggingUser(int expectedStatus) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatus).isEqualTo(actualStatusCode);
    }


    @And("A User Resource with values {string}, {string}, {string}, {string}, {string}, {int}")
    public void aUserResourceWithValues(String name, String lastName, String email, String password, String imageUrl, int phone) {
        UserResource resource = new UserResource()
                .withName(name)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withImageUrl(imageUrl)
                .withPhone(phone);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        UserResource actualResource;
        try {
            actualResource = mapper.readValue(value, UserResource.class);
        } catch (JsonProcessingException | NullPointerException e) {
            actualResource = new UserResource();
        }
        resource.setId(actualResource.getId());
        assertThat(resource).usingRecursiveComparison().isEqualTo(actualResource);
    }
}
