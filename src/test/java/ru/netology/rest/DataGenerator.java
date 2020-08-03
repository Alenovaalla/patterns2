package ru.netology.rest;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import java.util.Locale;
import static io.restassured.RestAssured.given;
import ru.netology.rest.Registration;

public class DataGenerator {

    public DataGenerator() {
    }

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void setUpAll(Registration user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static class RegistrationInfo {
        private RegistrationInfo() {
        }

        public static String userName(String Locale) {
            Faker faker = new Faker(new Locale(Locale));
            return faker.name().username();
        }

        public static String userPassword(String Locale) {
            Faker faker = new Faker(new Locale(Locale));
            return faker.internet().password();
        }


        public static Registration generateRegistration(String locale, boolean isBlocked) {
            return new Registration(
                    userName(locale),
                    userPassword(locale),
                    (isBlocked) ? "blocked" : "active");
        }

        public static Registration generateValidRegistration(String locale, boolean isBlocked) {
            Registration user = generateRegistration(locale, isBlocked);
            setUpAll(user);
            return user;
        }

        public static Registration generateInvalidLogin(String locale, boolean isBlocked) {
            String password = userPassword(locale);
            Registration user = new Registration(
                    "vasya",
                    password,
                    (isBlocked) ? "blocked" : "active");
            setUpAll(user);
            return new Registration(
                    "vera",
                    password,
                    (isBlocked) ? "blocked" : "active");
        }

        public static Registration generateInvalidPassword(String locale, boolean isBlocked) {
            String login = userName(locale);
            Registration user = new Registration(
                    login,
                    "password",
                    (isBlocked) ? "blocked" : "active");
            setUpAll(user);
            return new Registration(
                    login,
                    "a",
                    (isBlocked) ? "blocked" : "active");
        }
    }
}
