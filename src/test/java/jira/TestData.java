package jira;

public class TestData {

    // Тело запроса для создания issue в jira
    static String jsonString = "{\n" +
            "    \"fields\":{\n" +
            "        \"project\":{\n" +
            "            \"key\":\"HW05\"\n" +
            "        },\n" +
            "        \"summary\":\"jira test\",\n" +
            "        \"description\":{\n" +
            "            \"type\": \"doc\",\n" +
            "            \"version\": 1,\n" +
            "            \"content\": [\n" +
            "              {\n" +
            "              \"type\": \"paragraph\",\n" +
            "              \"content\": \n" +
            "               [\n" +
            "                 {\n" +
            "                   \"text\": \"Occurs on all orders\",\n" +
            "                   \"type\": \"text\"\n" +
            "                 }\n" +
            "\n" +
            "               ]\n" +
            "              }\n" +
            "            ]\n" +
            "        },\n" +
            "        \"issuetype\":\n" +
            "        {\n" +
            "            \"name\":\"Баг\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
}