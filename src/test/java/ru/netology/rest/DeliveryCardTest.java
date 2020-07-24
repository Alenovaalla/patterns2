package ru.netology.rest;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DeliveryCardTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");
    String date = formatter.format(LocalDateTime.now().plusDays(3));

    @Test
    void shouldTest() {
        open("http://localhost:7777");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Москва");
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue("Алла Аленова");
        form.$("[data-test-id=phone] input").setValue("+79211111111");
        form.$("[data-test-id=agreement]").click();
        form.$$("[role=button]").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).waitUntil(visible, 15000);

    }

}


