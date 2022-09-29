package com.upc.pe.backenderentcar.test.step.user;

import com.upc.pe.backenderentcar.user.domain.model.entity.User;
import com.upc.pe.backenderentcar.user.domain.persistence.UserRepository;
import com.upc.pe.backenderentcar.user.resource.CreateUserResource;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hibernate.mapping.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserProfileStepDefinition {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private String endPointPath;
    private int randomServerPort;
    private List<User> expectedUser;
    private UserRepository userRepository;
    private ResponseEntity<String> responseEntity;
    @Before
    public void setUp(){
        expectedUser = new ArrayList<>();
        userRepository.deleteAll();
    }
    @Given("The Endpoint {string} is available for logging users")
    public void theEndpointIsAvailableForLoggingUsers(String endpoint){
        this.endPointPath = String.format("http://localhost:%d/api/v1/clients", randomServerPort);
    }

    @When("a user request is sent")
    public void aUserRequestIsSent(String name, String lastName, String email, String password, String imageUrl, int phone) {
        CreateUserResource resource = new CreateUserResource()
                .withName(name)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withImageUrl(imageUrl)
                .withPhone(phone);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateUserResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }

    @Then("A Response with Status {int} is received for user request")
    public void aResponseWithStatusIsReceivedForUserRequest(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @When("an update user request is sent")
    public void anUpdateUserRequestIsSent(String name, String lastName, String email, String password, String imageUrl, int phone) {
        CreateUserResource resource = new CreateUserResource()
                .withName(name)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withImageUrl(imageUrl)
                .withPhone(phone);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateUserResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endPointPath, request, String.class);
    }
}
