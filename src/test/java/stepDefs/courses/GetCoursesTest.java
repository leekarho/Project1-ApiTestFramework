package stepDefs.courses;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
        courses = response.as(Course[].class);
        JSONObject firstCourse = (JSONObject) responseBody.get(0);
        MatcherAssert.assertThat(firstCourse.get("name"), Matchers.is("TECH 300"));
        MatcherAssert.assertThat(courses.length, Matchers.is(5));
    }

    @Then("HTTP status code should be {int} {string}")
    public void httpStatusCodeShouldBe(int statusCode, String arg1) {
        MatcherAssert.assertThat(statusCode, Matchers.is(response.getStatusCode()));
    }

}
