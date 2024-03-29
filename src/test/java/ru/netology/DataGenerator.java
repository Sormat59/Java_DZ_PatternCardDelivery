package ru.netology;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    public static Faker faker = new Faker(new Locale("ru"));

    @Step("Генерация города")
    public static String getCity() {
        return faker.address().cityName();
    }

@Step("Генерация даты доставки от 3-х до 120 дней от даты заполениния")
    public static String getDataRandom() {
        Random random = new Random();
        int randomDay = 3 + random.nextInt(120);
        return LocalDate.now().plusDays(randomDay).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

@Step("Генерация имени")
    public static String getName() {
        return faker.name().fullName();
    }
@Step("Генерация номера телефона")
    public static String getPhone() {
        return faker.phoneNumber().phoneNumber();

    }


}

