package stepDefs.login;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import pojos.PostLoginRequest;
import stepDefs.Utils;
import stepDefs.config.TestConfig;

import static org.hamcrest.Matchers.notNullValue;

public class LoginHappyPath {

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = TestConfig.getLoginPath();
    Response response;
    PostLoginRequest loginPayload;

    @Given("payload has a valid username and password")
    public void payloadHasAValidUsernameAndPassword() {
        loginPayload = new PostLoginRequest("sparta", "global");
    }

    @When("I send a POST request to the login endpoint")
    public void iSendAPOSTRequestToTheLoginEndpoint() {
        RequestSpecification requestSpecification = Utils.loginRequestSpecification(BASE_URI, PATH, loginPayload);
        response = RestAssured
                .given(requestSpecification)
                .when()
                .post()
                .thenReturn();
    }

    @Then("the HTTP status code should be {int} OK,")
    public void theHTTPStatusCodeShouldBeOK(int arg0) {
        Assertions.assertEquals(200, response.getStatusCode());
    }

    @And("the response should include an authentication token")
    public void theResponseShouldIncludeAnAuthenticationToken() {
        String token = response.jsonPath().getString("token");
        MatcherAssert.assertThat(token, notNullValue());
    }
}
