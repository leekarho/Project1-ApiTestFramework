package stepDefs.courses;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pojos.Course;
import stepDefs.ContextTest;
import stepDefs.Utils;
import stepDefs.config.TestConfig;

public class GetCourses {
    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = TestConfig.getCoursesPath();
    ContextTest context;
    private static JSONArray responseBody;
    private static Course[] courses;

    public GetCourses(ContextTest context) {
        this.context = context;
    }

    @Given("the courses endpoint is available")
    public void theCoursesEndpointIsAvailable() {
    }

    @When("I send a GET request to the courses endpoint")
    public void iSendAGETRequestToTheCoursesEndpoint() {
        context.response = RestAssured
                .given(CoursesUtils.getCoursesRequestSpec(BASE_URI, PATH))
                .when()
                .get()
                .thenReturn();

        responseBody = Utils.parseResponseToJsonArray(context.response);
    }

    @And("the response should include an array of all courses")
    public void theResponseShouldIncludeAnArrayOfAllCourses() {
        courses = context.response.as(Course[].class);
        JSONObject firstCourse = (JSONObject) responseBody.get(0);
        MatcherAssert.assertThat(firstCourse.get("name"), Matchers.is("TECH 300"));
        MatcherAssert.assertThat(courses.length, Matchers.is(5));
    }

}
