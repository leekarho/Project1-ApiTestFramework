package stepDefs.courses;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojos.Course;
import stepDefs.config.TestConfig;

public class GetCoursesTest {
    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String PATH = TestConfig.getCoursesPath();
    Response response;
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
    }
}
