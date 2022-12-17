package jira;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

public class GetTests{

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        PrintStream stream = new PrintStream(new FileOutputStream("log.txt"));
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
        request.baseUri(EndPoints.BASE_URL);
        // Проверка ответа
        request.get(EndPoints.USER_URL).then().spec(response).statusCode(200);
    }

    @Test
    public void statusCode404Test() {
        // RequestSpecification - класс спецификация ответа
        ResponseSpecification response = RestAssured.expect();
        // RequestSpecification - класс спецификация запроса
        RequestSpecification request = RestAssured.given();
        request.baseUri(EndPoints.BASE_URL);
        // Проверка ответа
        request.get(EndPoints.USER_URL_UNSUC).then().spec(response).statusCode(404);
    }
}
