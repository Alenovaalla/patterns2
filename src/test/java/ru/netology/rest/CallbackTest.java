package ru.netology.rest;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CallbackTest {
    @Test
    void shouldTest() {
        open("http://localhost:7777");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Алла");
        form.$("[data-test-id=phone] input").setValue("+79213333333");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $(".order-success").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}


