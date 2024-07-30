package stepDefs.spartans;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import pojos.Spartan;
import stepDefs.ContextTest;
import stepDefs.config.TestConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class PostSpartan {
    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = TestConfig.getSpartansPath();
    ContextTest context;
    private Map<String, Object> postSpartanTestData;
    private static Spartan spartan;

    public PostSpartan(ContextTest context) throws IOException {
        this.context = context;
    }

    private void loadTestData(String fileName) throws IOException {
        String filePath = "src/test/resources/TestData/" + fileName;
        ObjectMapper mapper = new ObjectMapper();
        postSpartanTestData = mapper.readValue(Files.readAllBytes(Paths.get(filePath)), new TypeReference<Map<String, Object>>() {});
    }

    @When("I send a POST request to the relevant endpoint with a valid JSON body")
    public void iSendAPOSTRequestToTheRelevantEndpointWithAValidJSONBody() throws IOException {
        loadTestData("postSpartan.json");

        context.response = RestAssured
                .given(SpartansUtils.postSpartansRequestSpecification(BASE_URI, PATH, context.bearerToken, postSpartanTestData))
                .when()
                .post()
                .thenReturn();
    }

    @And("the response should include the details of the newly created Spartan")
    public void theResponseShouldIncludeTheDetailsOfTheNewlyCreatedSpartan() {
        spartan = context.response.as(Spartan.class);
        MatcherAssert.assertThat(spartan.getId(), Matchers.is(1));
        MatcherAssert.assertThat(spartan.getFirstName(), Matchers.is("Sparty"));
        MatcherAssert.assertThat(spartan.getLastName(), Matchers.is("McFly"));
        MatcherAssert.assertThat(spartan.getUniversity(), Matchers.is("University of Rome"));
        MatcherAssert.assertThat(spartan.getDegree(), Matchers.is("Time Travel"));
        MatcherAssert.assertThat(spartan.getCourse(), Matchers.is("TECH 300"));
        MatcherAssert.assertThat(spartan.isGraduated(), Matchers.is(false));
        MatcherAssert.assertThat(spartan.getLinks(), Matchers.hasSize(4));
    }

    @When("I send a POST request to the relevant endpoint with payload with missing fields")
    public void iSendAPOSTRequestToTheRelevantEndpointWithPayloadWithMissingFields() throws IOException {
        loadTestData("invalidSpartan.json");

        context.response = RestAssured
                .given(SpartansUtils.postSpartansRequestSpecification(BASE_URI, PATH, context.bearerToken, postSpartanTestData))
                .when()
                .post()
                .thenReturn();
        
    }

    @And("the response should include error messages indicating the validation issues.")
    public void theResponseShouldIncludeErrorMessagesIndicatingTheValidationIssues() {
        MatcherAssert.assertThat(context.response.jsonPath().getMap("errors"), Matchers.notNullValue());
    }
}
