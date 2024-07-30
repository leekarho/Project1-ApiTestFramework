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
import stepDefs.CommonSteps;
import stepDefs.ContextTest;
import stepDefs.Utils;
import stepDefs.config.TestConfig;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

public class AuthLoginTest {

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = TestConfig.getLoginPath();
    ContextTest context;
    PostLoginRequest loginPayload;
    boolean isEmptyPayload = false;

    public AuthLoginTest(ContextTest context) {
        this.context = context;
    }

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

        context.response = RestAssured
                .given(requestSpecification)
                .when()
                .post()
                .thenReturn();
    }

    @And("the response should include an authentication token")
    public void theResponseShouldIncludeAnAuthenticationToken() {
        context.bearerToken = context.response.jsonPath().getString("token");
        MatcherAssert.assertThat(context.bearerToken, notNullValue());
    }

    @Given("payload has an invalid username and password")
    public void payloadHasAnInvalidUsernameAndPassword() {
        loginPayload = new PostLoginRequest("abc", "123");
    }

    @And("the response should include an error message")
    public void theResponseShouldIncludeAnErrorMessage() {
        String message = context.response.jsonPath().getString("title");
        MatcherAssert.assertThat(message, notNullValue());
    }

    @Given("payload has is an empty body")
    public void payloadHasIsAnEmptyBody() {
        isEmptyPayload = true;
    }

}
