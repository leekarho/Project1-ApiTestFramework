package stepDefs.courses;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

public class CoursesUtils {

    public static RequestSpecification getCoursesRequestSpec(String baseUri, String path) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .build();
    }

    public static RequestSpecification getSpecificCourseRequestSpec(String baseUri, String path, int courseId) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addPathParam("courseId", courseId)
                .build();
    }

    public static RequestSpecification getSpecificCourseRequestSpecInvalid(String baseUri, String path, String invalidId) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addPathParam("courseId", invalidId)
                .build();
    }
}
