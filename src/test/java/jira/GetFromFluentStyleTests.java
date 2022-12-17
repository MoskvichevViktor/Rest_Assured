package jira;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetFromFluentStyleTests {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = EndPoints.BASE_URL;
        if (RestAssured.filters().isEmpty()) {
            RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL),
                    new ResponseLoggingFilter(LogDetail.ALL));
        }
    }

    @Test
    public void statusCode200Test() {
        RestAssured
                // В given задаем спецификацию отправляемого запроса
                .given()
                // В when отправляем запрос и получаем ответ
                .when()
                    // Отправка HTTP запроса GET
                    .get(EndPoints.USER_URL)
                // В then задаем спецификацию получаемого ответа (проверка)
                // Статус, заголовки, тело ответа и т д
                .then()
                    // Проверка кода статуса ответа
                    .statusCode(200);
    }

    @Test
    public void statusCode404Test() {
        RestAssured
                // В given задаем спецификацию отправляемого запроса
                .given()
                // В expect задаем спецификацию получаемого ответа (проверка)
                // Статус, заголовки, тело ответа и т д
                .expect()
                    .statusCode(404)
                // В when отправляем запрос и получаем ответ
                .when()
                    .get(EndPoints.USER_URL_UNSUC);
    }
}
