package jira;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetFromFluentStyleTests {

    final String BASE_URL = "https://zil34.atlassian.net";
    final String USER_URL = "/jira/software/projects/HW05/boards/1";
    final String USER_URL_UNSUC = "/rickandmortyapi.com/api/character";

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void statusCode200Test() {
        RestAssured
                // В given задаем спецификацию отправляемого запроса
                .given().log().all()
                // В when отправляем запрос и получаем ответ
                .when()
                    // Отправка HTTP запроса GET
                    .get(USER_URL)
                // В then задаем спецификацию получаемого ответа (проверка)
                // Статус, заголовки, тело ответа и т д
                .then().log().all()
                    // Проверка кода статуса ответа
                    .statusCode(200);
    }

    @Test
    public void statusCode404Test() {
        RestAssured
                // В given задаем спецификацию отправляемого запроса
                .given().log().all()
                // В expect задаем спецификацию получаемого ответа (проверка)
                // Статус, заголовки, тело ответа и т д
                .expect().log().all()
                    .statusCode(404)
                // В when отправляем запрос и получаем ответ
                .when()
                    .get(USER_URL_UNSUC);
    }
}
