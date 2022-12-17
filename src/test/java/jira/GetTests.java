package jira;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetTests extends BaseTest{

    final String BASE_URL = "https://zil34.atlassian.net";
    final String USER_URL = "/jira/software/projects/HW05/boards/1";
    final String USER_URL_UNSUC = "/rickandmortyapi.com/api/character";

    @BeforeEach
    public void setUp() {
        if (RestAssured.filters().isEmpty()) {
            RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL),
                    new ResponseLoggingFilter(LogDetail.ALL));
        }
    }

    @Test
    public void statusCode200Test() {
        // RequestSpecification - класс спецификация ответа
        ResponseSpecification response = RestAssured.expect();
        // RequestSpecification - класс спецификация запроса
        RequestSpecification request = RestAssured.given();
        request.baseUri(BASE_URL);
        // Проверка ответа
        request.get(USER_URL).then().spec(response).statusCode(200);
    }

    @Test
    public void statusCode404Test() {
        // RequestSpecification - класс спецификация ответа
        ResponseSpecification response = RestAssured.expect();
        // RequestSpecification - класс спецификация запроса
        RequestSpecification request = RestAssured.given();
        request.baseUri(BASE_URL);
        // Проверка ответа
        request.get(USER_URL_UNSUC).then().spec(response).statusCode(404);
    }
}
