package ru.netology.rest;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.rest.DataGenerator.RegistrationInfo.*;


public class TestMode {

    public void login (String login, String password) {
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue(login);
        $("[data-test-id=password] input").setValue(password);
        $("[data-test-id=action-login]").click();
    }

    @Test
    public void shouldPassWithActiveValid() {
        Registration user = generateValidRegistration("en", false);
        login(user.getLogin(), user.getPassword());
        $(byText("Личный кабинет")).waitUntil(Condition.visible, 15000);
    }

    @Test
    public void shouldErrorIfNotRegisteredUser() {
        Registration user = generateRegistration("en", false);
        login(user.getLogin(), user.getPassword());
        $(withText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }

    @Test
    public void shouldErrorIfUserBlocked() {
        Registration user = generateValidRegistration("en", true);
        login(user.getLogin(), user.getPassword());
        $(withText("Пользователь заблокирован")).waitUntil(Condition.visible, 15000);
    }

    @Test
    public void shouldErrorIfInvalidLogin() {
        Registration user = generateInvalidLogin("en", false);
        login(user.getLogin(), user.getPassword());
        $(withText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }

    @Test
    public void shouldGetErrorIfInvalidPassword() {
        Registration user = generateInvalidPassword("en", false);
        login(user.getLogin(), user.getPassword());
        $(withText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }
}


