package stepDefs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pojos.PostLoginRequest;

public class Utils {

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

    public static RequestSpecification loginRequestSpecification(String baseUri, String path, PostLoginRequest body) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .setBody(body)
                .build();
    }

    public static RequestSpecification emptyBodyRequestSpecification(String baseUri, String path) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .build();
    }
}
