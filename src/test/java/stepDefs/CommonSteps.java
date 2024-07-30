package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import pojos.PostLoginRequest;

import static org.hamcrest.Matchers.notNullValue;

public class CommonSteps {
    PostLoginRequest loginPayload;

    ContextTest context;

    public CommonSteps(ContextTest context) {
        this.context = context;
    }

    @Then("the HTTP status code should be {int} {string}")
    public void theHTTPStatusCodeShouldBe(int statusCode, String expectedStatus) {
        MatcherAssert.assertThat(context.response.getStatusCode(), Matchers.is(statusCode));
        MatcherAssert.assertThat(expectedStatus, Matchers.is(expectedStatus));
    }

    @Given("I have a valid bearer token")
    public void iHaveAValidBearerToken() {
        loginPayload = new PostLoginRequest("sparta", "global");
        context.response = RestAssured
                .given(Utils.loginRequestSpecification("http://localhost:8080", "/Auth/login", loginPayload))
                .when()
                .post()
                .thenReturn();
        context.bearerToken = context.response.jsonPath().getString("token");
    }


}
