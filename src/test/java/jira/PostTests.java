package jira;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostTests extends BaseTest{

    //private final String BASE_URL = "https://zil34.atlassian.net";
    private final String NEW_ISSUE_URL = "/rest/api/3/issue";
    private final String NEW_ISSUE_URL_UNSUC = "/rest/api/issue";

    @BeforeEach
    public void setUp() {
        if (RestAssured.filters().isEmpty()) {
            RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL),
                    new ResponseLoggingFilter(LogDetail.ALL));
        }
    }

    @Test
    public void statusCode201Test() {

        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec());
        // Response - класс, хранящий ответ запроса
        // Отправка запроса POST
        Response response = RestAssured.post(NEW_ISSUE_URL);
        // Проверка кода статуса ответа
        response.then().statusCode(201);
    }

    @Test
    public void statusCode404Test() {

        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec());
        // Response - класс, хранящий ответ запроса
        // Отправка HTTP запроса POST
        // Передадим не верный URL
        Response response = RestAssured.post(NEW_ISSUE_URL_UNSUC);
        // Проверка кода статуса ответа
        response.then().statusCode(404);
    }

    @Test
    public void contentTypeTest() {

        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec());
        // Response - класс, хранящий ответ запроса
        // Отправка HTTP запроса POST
        Response response = RestAssured.post(NEW_ISSUE_URL);
        // Проверка кода статуса ответа
        response.then().contentType(ContentType.JSON);
    }
}
