import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SingleUserTests extends BaseClass{

    @Test
    void getSingleStatusCode200Test(){
        given().
                log().uri()
                .get("users/2")
                .then()
                .statusCode(200)
                .log().all()
                .body("data.id", is(3));
    }
}
