package stepDefs.courses;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class CoursesUtils {



    public static RequestSpecification getCoursesRequestSpec(String baseUri, String path) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .build();
    }
}
