package jira;

import org.junit.jupiter.api.AfterEach;

public class BaseTest {

    //Метод удаления объявленных спецификаций после завершения выполнения тестов в классе
    @AfterEach
    public void tearSpec() {
        Specifications.deleteSpec();
    }
}
