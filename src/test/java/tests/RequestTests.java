package tests;

import models.ResponseUser;
import models.UserDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static spec.CreateSpec.*;
import static spec.UpdateSpec.responseSpecUpdate;

public class RequestTests extends TestBase {

    @Test
    @DisplayName("GET /users/{id} status 200")
    void getSingleStatusCode200Test() {
        given().
                spec(requestSpec)
                .get("users/2")
                .then()
                .statusCode(200)
                .log().all()
                .body("data.id", is(2));
    }


    @DisplayName("GET /users/{id} status 404")
    @Test
    void getSingleStatusCode404Test() {
        given().
                spec(requestSpec)
                .get("users/0")
                .then()
                .statusCode(404)
                .log().all();

    }

    @DisplayName("POST /users status 201")
    @Test
    void postCreateUserStatus201Test() {

        UserDate userDate = new UserDate();
        userDate.setName("morpheus");
        userDate.setJob("leader");


        ResponseUser response = step("Создание нового пользователя", ()-> given().
                spec(requestSpec)
                .body(userDate)
                .post("users")
                .then()
                .spec(createUserResponse)
                .extract().as(ResponseUser.class));


        step("Проверка корректности имени юзера", ()->
                        assertThat(userDate.getName()).isEqualTo(response.getName())
                );

        step("Проверка корректности работы юзера", ()->
                        assertThat(userDate.getJob()).isEqualTo(response.getJob())
        );

    }
    @DisplayName("PATCH /users/{id} status 200")
    @Test
    void patchUpdateUsersStatus200Test() {
        UserDate userDateUpdate = new UserDate();
        userDateUpdate.setName("ivan");


        ResponseUser responseUserUpdated = step("Обновление имени пользователя",() -> given().
                spec(requestSpec)
                .body(userDateUpdate)
                .patch("users/2")
                .then()
                .spec(responseSpecUpdate)
                .extract().as(ResponseUser.class));

        step("Проверка корректности обновления имени", ()->
                assertThat(userDateUpdate.getName()).isEqualTo(responseUserUpdated.getName())
        );

    }

    @DisplayName("DELETE /users/{id} status 204")
    @Test
    void deleteUsers204Test() {
        given().
                spec(requestSpec)
                .delete("users/2")
                .then()
                .log().all()
                .statusCode(204);

    }

}


