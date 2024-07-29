package stepDefs;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class CommonSteps {

    ContextTest context;

    public CommonSteps(ContextTest context) {
        this.context = context;
    }

    @Then("the HTTP status code should be {int} {string}")
    public void theHTTPStatusCodeShouldBe(int statusCode, String expectedStatus) {
        MatcherAssert.assertThat(context.response.getStatusCode(), Matchers.is(statusCode));
        MatcherAssert.assertThat(expectedStatus, Matchers.is(expectedStatus));
    }
}
