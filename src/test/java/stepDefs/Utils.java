package stepDefs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.PostLoginRequest;

public class Utils {

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
