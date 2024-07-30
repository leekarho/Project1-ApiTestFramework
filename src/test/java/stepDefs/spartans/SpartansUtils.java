package stepDefs.spartans;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class SpartansUtils {

    public static RequestSpecification getSpartansRequestSpecification(String baseUri, String path, String token) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of("Authorization", "Bearer " + token))
                .build();
    }

    public static RequestSpecification postSpartansRequestSpecification(String baseUri, String path, String token, Object spartan) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(path)
                .addHeaders(Map.of("Authorization", "Bearer " + token))
                .setContentType(ContentType.JSON)
                .setBody(spartan)
                .build();
    }
}
