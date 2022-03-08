package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {


    @Test
    public void shouldRegByAcc() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").val(DataGenerator.getCity());

        String date = DataGenerator.getDataRandom();

        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id='name'] input").val(DataGenerator.getName());
        $("[data-test-id='phone'] input").val(DataGenerator.getPhone());
        $("span.checkbox__box").click();
        $(withText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible);
        $("[data-test-id='success-notification']").shouldBe(visible).shouldHave(Condition.text("Встреча успешно запланирована на " + date));
        $("[data-test-id='date'] input").setValue(DataGenerator.getDataRandom());
        $(withText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible);
        $("[data-test-id=replan-notification] button.button").click();
        $(withText("Успешно")).shouldBe(visible);

    }


}
