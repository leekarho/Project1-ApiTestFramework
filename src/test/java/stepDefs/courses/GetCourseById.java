package stepDefs.courses;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import pojos.Course;
import stepDefs.ContextTest;
import stepDefs.config.TestConfig;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class GetCourseById {

    private static final String BASE_URI = TestConfig.getBaseUri();
    private static final String COURSE_PATH = TestConfig.getCoursesPath() + "/{courseId}";
    ContextTest context;
    private static Course course;

    public GetCourseById(ContextTest context) {
        this.context = context;
    }

    @Given("the course id {int} does not exist")
    public void theCourseIdDoesNotExist(int arg0) {
    }

    @Given("the course with id {int} exists")
    public void theCourseWithIdExists(int id) {
    }

    @When("I send a GET request to the courses endpoint with id {int}")
    public void iSendAGETRequestToTheCoursesEndpointWithId(int courseId) {
        context.response = RestAssured
                .given(CoursesUtils.getSpecificCourseRequestSpec(BASE_URI, COURSE_PATH, courseId))
                .when()
                .get()
                .thenReturn();
    }

    @And("the response should include the details of the course with ID {int}.")
    public void theResponseShouldIncludeTheDetailsOfTheCourseWithID(int arg0) {
        course = context.response.as(Course.class);
        MatcherAssert.assertThat(course.getId(), Matchers.is(1));
        MatcherAssert.assertThat(course.getName(), Matchers.is("TECH 300"));
        MatcherAssert.assertThat(course.getStream(), Matchers.is("C# Dev"));
        MatcherAssert.assertThat(course.getTrainer(), Matchers.is("Phil Windridge"));
        MatcherAssert.assertThat(course.getSpartans(), Matchers.contains(
                "Sparty McFly",
                "George Harrison",
                "Beyonce Knowles",
                "Dua Lipa",
                "Ada Lovelace",
                "Rosalind Franklin",
                "Wonder Woman"
        ));
        MatcherAssert.assertThat(course.getStartDate(), Matchers.is("2023-03-01T00:00:00"));
        MatcherAssert.assertThat(course.getEndDate(), Matchers.is("2023-05-01T00:00:00"));
        MatcherAssert.assertThat(course.getLinks(), Matchers.hasSize(1));
        MatcherAssert.assertThat(course.getLinks().get(0).getHref(), Matchers.is("/api/Courses/1"));
        MatcherAssert.assertThat(course.getLinks().get(0).getRel(), Matchers.is("self"));
        MatcherAssert.assertThat(course.getLinks().get(0).getMethod(), Matchers.is("GET"));
    }

    @Disabled("Disabled until API is fixed to return appropriate error message, See defect report: 2")
    @And("response should include an error message")
    public void responseShouldIncludeAnErrorMessage() {
        String message = context.response.jsonPath().getString("errors");
        Map<String, List<String>> errors = context.response.jsonPath().getMap("errors");
        MatcherAssert.assertThat(errors, hasKey("id"));
        MatcherAssert.assertThat(message, notNullValue());
    }

    @Given("the id provided is invalid")
    public void theIdProvidedIsInvalid() {
        
    }

    @When("I send a GET request to the courses endpoint with {string}")
    public void iSendAGETRequestToTheCoursesEndpointWith(String invalidId) {
        context.response = RestAssured
                .given(CoursesUtils.getSpecificCourseRequestSpecInvalid(BASE_URI, COURSE_PATH, invalidId))
                .when()
                .get()
                .thenReturn();
    }

}
