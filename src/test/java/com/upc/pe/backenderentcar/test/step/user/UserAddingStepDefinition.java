package com.upc.pe.backenderentcar.test.step.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.pe.backenderentcar.user.resource.CreateUserResource;
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

public class UserAddingStepDefinition {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int randomServerPort;
    private String endPointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The Endpoint {string} is available for users")
    public void theEndpointIsAvailableForUsers(String endPointPath) {
        this.endPointPath = String.format("http://localhost:%d/api/v1/users", randomServerPort);
    }

    @When("A User Request is sent with values {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void aUserRequestIsSentWithValues(String name, String lastName, String typeOfUser, String email, String password, String imageUrl, String phone) {
        CreateUserResource resource = new CreateUserResource()
                .withName(name)
                .withLastName(lastName)
                .withTypeOfUser(typeOfUser)
                .withEmail(email)
                .withPassword(password)
                .withImageUrl(imageUrl)
                .withPhone(phone);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateUserResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received for the user")
    public void aResponseWithStatusIsReceivedForTheUser(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @And("A User Resource is sent with values {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void aUserResourceIsSentWithValues(String name, String lastName, String typeOfUser, String email, String password, String imageUrl, String phone) {
        UserResource resource = new UserResource()
                .withName(name)
                .withLastName(lastName)
                .withTypeOfUser(typeOfUser)
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
