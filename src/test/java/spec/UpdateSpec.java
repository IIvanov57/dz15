package spec;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class UpdateSpec {
    public static RequestSpecification requestSpecUpdate = with()
            .filter(new AllureRestAssured())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(ContentType.JSON);

    public static ResponseSpecification responseSpecUpdate = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

}
