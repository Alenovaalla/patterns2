package ru.netology.rest;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.rest.DataGenerator.generateUser;


public class DeliveryOrder {
    Registration user = generateUser();

    @Test
    void sholdPlanningCardDeliveryOrder() {
        open("http://localhost:7777");
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(user.getDate());
        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("[role=button]").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на")).shouldHave(text(user.getDate()));
    }

    @Test
    void sholdRescheduleCardDelivery() {
        open("http://localhost:7777");
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(user.getDate());
        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(user.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("[role=button]").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(user.getDate());
        $$("[role=button]").find(exactText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).waitUntil(visible, 15000);
        $$("div.notification__content > button").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на")).shouldHave(text(user.getDate()));
    }

}
