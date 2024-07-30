package stepDefs.spartans;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojos.Spartan;
import stepDefs.ContextTest;
import stepDefs.Utils;
import stepDefs.config.TestConfig;


public class GetSpartans {

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = TestConfig.getSpartansPath();
    ContextTest context;
    private static JSONArray responseBody;
    private static Spartan[] spartans;

    public GetSpartans(ContextTest context) {
        this.context = context;
    }

    @Given("I have an invalid bearer token")
    public void iHaveAnInvalidBearerToken() {
        context.bearerToken = "123";
    }

    @When("I send a Get request to the Spartans endpoint")
    public void iSendAGetRequestToTheSpartansEndpoint() {
        context.response = RestAssured
                .given(SpartansUtils.getSpartansRequestSpecification(BASE_URI, PATH, context.bearerToken))
                .when()
                .get()
                .thenReturn();
    }

    @And("the response should include an array of all Spartans")
    public void theResponseShouldIncludeAnArrayOfAllSpartans() {
        spartans = context.response.as(Spartan[].class);
        MatcherAssert.assertThat(spartans.length, Matchers.is(33));
    }

}
