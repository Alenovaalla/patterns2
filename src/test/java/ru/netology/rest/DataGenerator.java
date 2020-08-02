package ru.netology.rest;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {

    public DataGenerator() {
    }

    public static String getCity() {
        String[] city = new String[]{
                "Анапа", "Белгород", "Выборг", "Грозный", "Есентуки", "Иркутск", "Йошкар-Ола",
                "Калуга", "Липецк", "Москва", "Новосибирск", "Омск", "Пермь", "Петрозаводск",
                "Петропавловск-Камчатский", "Псков", "Рыбинск", "Санкт-Петербург", "Тверь", "Уфа", "Хабаровск"};
        Random random = new Random();
        int index = random.nextInt(city.length);
        return city[index];
    }


    public static String getDate(int daysToAdd) {
        LocalDate Date = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return Date.format(formatter);
    }

    public static Registration generateUser() {
        Faker faker = new Faker(new Locale("ru"));
        return new Registration(
                getCity(),
                getDate(3),
                faker.name().lastName() + " " + faker.name().firstName(),
                faker.phoneNumber().phoneNumber());

    }
}