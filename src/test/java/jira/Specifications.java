package jira;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    //Метод содержит спесификацию для отправки запроса
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://zil34.atlassian.net")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", Config.getBasicAuthHeaderValue())
                .setBody(TestData.jsonString)
                .build();
    }

    //Метод содержит спесификацию для получения ответа
    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .build();
    }

    //Метод для установки объявленных спецификаций
    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    //Метод для удаления спецификаций
    public static void deleteSpec() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }
}
