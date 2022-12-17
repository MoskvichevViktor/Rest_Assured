package jira;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;

public class PostFromFluentStyleTests {



    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = EndPoints.BASE_URL;
        if (RestAssured.filters().isEmpty()) {
            RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL),
                    new ResponseLoggingFilter(LogDetail.ALL));
        }
    }

    @Test
    public void statusCode201Test() {

        RestAssured
                .given()
                    .header("Authorization", Config.getBasicAuthHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(TestData.jsonString)
                .when()
                    // Отправка HTTP запроса POST
                    .post(EndPoints.NEW_ISSUE_URL)
                .then()
                    // Проверка кода статуса ответа
                    .statusCode(201);
    }

    @Test
    public void statusCode401Test() {

        RestAssured
                .given()
                    // Закоментируем поле с авторизацией для получение кода статуса 401
                    //.header("Authorization", Config.getBasicAuthHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(TestData.jsonString)
                // В expect задаем спецификацию получаемого ответа (проверка)
                // Статус, заголовки, тело ответа и т д
                .expect()
                    .statusCode(401)
                // В when отправка HTTP запроса POST
                .when()
                    .post(EndPoints.NEW_ISSUE_URL);
    }


    @Test
    public void statusCode404Test() {

        RestAssured
                .given()
                    .header("Authorization", Config.getBasicAuthHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(TestData.jsonString)
                // В expect задаем спецификацию получаемого ответа (проверка)
                // Статус, заголовки, тело ответа и т д
                .expect()
                    .statusCode(404)
                // В when отправка HTTP запроса POST
                .when()
                    // Передадим не верный URL
                    .post(EndPoints.NEW_ISSUE_URL_UNSUC);
    }

    @Test
    public void contentTypeTest() {

        RestAssured
                .given()
                    .header("Authorization", Config.getBasicAuthHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(TestData.jsonString)
                .when()
                    // Отправка HTTP запроса POST
                    .post(EndPoints.NEW_ISSUE_URL)
                .then()
                    // Проверка ContentType
                    .contentType(ContentType.JSON);
    }

    @Test
    public void bodyContainsKeyTest() {

        RestAssured
                .given()
                    .header("Authorization", Config.getBasicAuthHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(TestData.jsonString)
                .when()
                    // Отправка HTTP запроса POST
                    .post(EndPoints.NEW_ISSUE_URL)
                .then()
                    // Проверка наличия в ответе после создание issue ключа - индификатора  проекта
                    .body("key", containsString(EndPoints.keyProject));
    }
}
