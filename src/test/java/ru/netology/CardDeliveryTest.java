package ru.netology;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {


    @BeforeAll
    static void setUp() {
        Faker faker = new Faker(new Locale("ru"));
    }


    @Test
    public void shouldRegByAcc() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").val(DataGenerator.getCity());

        String date = DataGenerator.getDataRandom();

        $("[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id='name'] input").val(DataGenerator.getName());
        $("[data-test-id='phone'] input").val(DataGenerator.getPhone());
        $("span.checkbox__box").click();
        $(withText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible);
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + date));

        $("[data-test-id='date'] input").setValue(DataGenerator.getDataRandom());
        $(withText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible);
        $("[data-test-id=replan-notification] button.button").click();
        $(withText("Успешно")).shouldBe(visible);


    }


}
