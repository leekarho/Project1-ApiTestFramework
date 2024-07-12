package stepDefs.courses;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

public class CoursesUtils {

    public static JSONArray parseResponseToJsonArray(Response response){

        JSONArray responseBody = null;
        JSONParser parser = new JSONParser();
        try{
            responseBody = (JSONArray) parser.parse(response.getBody().asString());
        }catch(ParseException e){
            e.printStackTrace();
        }
        return responseBody;
    }

    public static RequestSpecification getCoursesRequestSpec(String baseUri, String path) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .build();
    }

    public static RequestSpecification getSpecificGCourseRequestSpec(String baseUri, String path, int courseId) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addPathParam("courseId", courseId)
                .build();
    }

    public static RequestSpecification getSpecificGCourseRequestSpecInvalid(String baseUri, String path, String invalidId) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addPathParam("courseId", invalidId)
                .build();
    }
}
