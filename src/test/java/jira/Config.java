package jira;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Config {

    public static String getBasicAuthHeaderValue() {
        String authCreds = String.format("%s:%s", "zil34@yandex.ru", "token");
        return "Basic " + new String(Base64.getEncoder().encode(authCreds.getBytes(StandardCharsets.UTF_8)));
    }
}
