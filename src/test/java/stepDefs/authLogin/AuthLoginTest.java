package stepDefs.authLogin;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import pojos.PostLoginRequest;
import stepDefs.Utils;
import stepDefs.config.TestConfig;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

public class AuthLoginTest {

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = TestConfig.getLoginPath();
    Response response;
    PostLoginRequest loginPayload;
    boolean isEmptyPayload = false;

    @Given("payload has a valid username and password")
    public void payloadHasAValidUsernameAndPassword() {
        loginPayload = new PostLoginRequest("sparta", "global");
    }

    @When("I send a POST request to the login endpoint")
    public void iSendAPOSTRequestToTheLoginEndpoint() {
        RequestSpecification requestSpecification;
        if (isEmptyPayload) {
            requestSpecification = Utils.emptyBodyRequestSpecification(BASE_URI, PATH);
        } else {
            requestSpecification = Utils.loginRequestSpecification(BASE_URI, PATH, loginPayload);
        }

        response = RestAssured
                .given(requestSpecification)
                .when()
                .post()
                .thenReturn();
    }

    @And("the response should include an authentication token")
    public void theResponseShouldIncludeAnAuthenticationToken() {
        String token = response.jsonPath().getString("token");
        MatcherAssert.assertThat(token, notNullValue());
    }

    @Given("payload has an invalid username and password")
    public void payloadHasAnInvalidUsernameAndPassword() {
        loginPayload = new PostLoginRequest("abc", "123");
    }

    @And("the response should include an error message")
    public void theResponseShouldIncludeAnErrorMessage() {
        String message = response.jsonPath().getString("title");
        MatcherAssert.assertThat(message, notNullValue());
    }

    @Given("payload has is an empty body")
    public void payloadHasIsAnEmptyBody() {
        isEmptyPayload = true;
    }

    @Then("the HTTP status code should be {int} {string}")
    public void theHTTPStatusCodeShouldBe(int statusCode, String arg1) {
        System.out.println(response.getStatusCode());
        MatcherAssert.assertThat(statusCode, Matchers.is(response.getStatusCode()));
    }


}
