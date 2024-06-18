import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ReqresTests extends BaseClass {

    @Test
    void getSingleStatusCode200Test() {
        given().
                log().all()
                .get("users/2")
                .then()
                .statusCode(200)
                .log().all()
                .body("data.id", is(2));
    }


    @Test
    void getSingleStatusCode404Test() {
        given().
                log().all()
                .get("users/0")
                .then()
                .statusCode(404)
                .log().all();

    }

    @Test
    void postCreateUserStatus201Test() {
        given().
                log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));

    }

    @Test
    void patchUpdateUsersStatus200Test() {
        given().
                log().all()
                .contentType(ContentType.JSON)
                .body("{ \"name\": \"ivan\"}")
                .patch("users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name",is("ivan"));

    }

    @Test
    void deleteUsers204Test() {
        given().
                log().all()
                .delete("users/2")
                .then()
                .log().all()
                .statusCode(204);

    }
    

}

