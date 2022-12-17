package jira;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;

public class PostFromFluentStyleTests {

    final String BASE_URL = "https://zil34.atlassian.net";
    final String NEW_ISSUE_URL = "/rest/api/3/issue";
    final String NEW_ISSUE_URL_UNSUC = "/rest/api/issue";
    private String keyProject = "HW05";

    @BeforeEach
    public void setUp() {
        RestAssured
                .baseURI = BASE_URL;
    }

    @Test
    public void statusCode201Test() {

        RestAssured
                .given().log().all()
                    .header("Authorization", Config.getBasicAuthHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(TestData.jsonString)
                .when()
                    // Отправка HTTP запроса POST
                    .post(NEW_ISSUE_URL)
                .then().log().all()
                    // Проверка кода статуса ответа
                    .statusCode(201);
    }

    @Test
    public void statusCode401Test() {

        RestAssured
                .given().log().all()
                    // Закоментируем поле с авторизацией для получение кода статуса 401
                    //.header("Authorization", Config.getBasicAuthHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(TestData.jsonString)
                // В expect задаем спецификацию получаемого ответа (проверка)
                // Статус, заголовки, тело ответа и т д
                .expect().log().all()
                    .statusCode(401)
                // В when отправка HTTP запроса POST
                .when()
                    .post(NEW_ISSUE_URL);
    }


    @Test
    public void statusCode404Test() {

        RestAssured
                .given().log().all()
                    .header("Authorization", Config.getBasicAuthHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(TestData.jsonString)
                // В expect задаем спецификацию получаемого ответа (проверка)
                // Статус, заголовки, тело ответа и т д
                .expect().log().all()
                    .statusCode(404)
                // В when отправка HTTP запроса POST
                .when()
                    // Передадим не верный URL
                    .post(NEW_ISSUE_URL_UNSUC);
    }

    @Test
    public void contentTypeTest() {

        RestAssured
                .given().log().all()
                    .header("Authorization", Config.getBasicAuthHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(TestData.jsonString)
                .when()
                    // Отправка HTTP запроса POST
                    .post(NEW_ISSUE_URL)
                .then().log().all()
                    // Проверка ContentType
                    .contentType(ContentType.JSON);
    }

    @Test
    public void bodyContainsKeyTest() {

        RestAssured
                .given().log().all()
                    .header("Authorization", Config.getBasicAuthHeaderValue())
                    .contentType(ContentType.JSON)
                    .body(TestData.jsonString)
                .when()
                    // Отправка HTTP запроса POST
                    .post(NEW_ISSUE_URL)
                .then().log().all()
                    // Проверка наличия в ответе после создание issue ключа - индификатора  проекта
                    .body("key", containsString(keyProject));
    }
}
