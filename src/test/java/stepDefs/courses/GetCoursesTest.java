package stepDefs.courses;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import pojos.Course;
import stepDefs.config.TestConfig;

public class GetCoursesTest {
    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = TestConfig.getCoursesPath();
    private static Response response;
    private static JSONArray responseBody;
    private static Course[] courses;

    @Given("the courses endpoint is available")
    public void theCoursesEndpointIsAvailable() {
    }

    @When("I send a GET request to the courses endpoint")
    public void iSendAGETRequestToTheCoursesEndpoint() {
        response = RestAssured
                .given(CoursesUtils.getCoursesRequestSpec(BASE_URI, PATH))
                .when()
                .get()
                .thenReturn();

        responseBody = CoursesUtils.parseResponseToJsonArray(response);
    }

    @And("the response should include an array of all courses")
    public void theResponseShouldIncludeAnArrayOfAllCourses() {
    }
}
