package API;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HeaderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

public class Configuration {


    static String domain = "https://172.20.10.3:5001";

    public static String getDomain() {
        return domain;
    }

    /*
     * Установка парсера данных по умолчанию. Стандартный парсер - JSON
     * */
    public static void initializeRestAssuredSettings() {
        RestAssured.defaultParser = Parser.JSON;
    }

    /**
     * Устанавливает настройки для запроса на игнорирование неподтверждённых SSL
     * сертификатов Без этой настройки тесты откажутся работать с API
     */
    public static RequestSpecification getRequestSpec() {
        initializeRestAssuredSettings();

        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.setRelaxedHTTPSValidation();

        RequestSpecification spec = specBuilder.build();
        spec.contentType(ContentType.JSON);
        spec.accept(ContentType.JSON);

        return spec;
    }

    public static RequestSpecification getRequestSpec(String token) {
        RequestSpecification spec = getRequestSpec();
        spec.header("Authorization", "Bearer " + token);

        return spec;
    }

    public static RestAssuredConfig getConfig() {
        RestAssuredConfig config = new RestAssuredConfig();
        return config;
    }
}