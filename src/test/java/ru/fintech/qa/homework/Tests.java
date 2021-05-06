package ru.fintech.qa.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.fintech.qa.homework.utils.BeforeUtils;
import ru.fintech.qa.homework.utils.DbClient;
import ru.fintech.qa.homework.utils.models.Animal;
import ru.fintech.qa.homework.utils.models.Places;
import ru.fintech.qa.homework.utils.models.Workman;
import ru.fintech.qa.homework.utils.models.Zoo;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    @BeforeAll
    public static void createDb() {
        BeforeUtils.createData();
    }

    @Test
    public void testAnimalAmount() {
        Assertions.assertEquals(10, new DbClient().count("animal").size());
    }

    @Test
    public void testAnimalInsertIndexFrom1To10() {
        Animal animal = new Animal();
        for (int i = 1; i <= 10; i++) {
            animal.setId(i);
            Assertions.assertFalse(DbClient.insert(animal));
        }
    }

    @Test
    public void testInsertNullInWorkman() {
        Workman workman = new Workman();
        workman.setName(null);
        Assertions.assertFalse(DbClient.insert(workman));
    }

    @Test
    public void testCountPlaces() {
        Places places = new Places();
        places.setId(6);
        DbClient.insert(places);
        Assertions.assertEquals(6, new DbClient().countPlaces().size());
    }

    @Test
    public void testZooNamesAndCount() {
        ArrayList<String> expectedZooList = new ArrayList<>() {
            {
                add("Центральный");
                add("Северный");
                add("Западный");
            }
        };
        ArrayList<String> actualZooList = new ArrayList<>();
        List<Zoo> zooList = new DbClient().getZoos();
        Assertions.assertEquals(3, zooList.size());
        zooList.forEach(zoo -> actualZooList.add(zoo.getName()));
        Assertions.assertIterableEquals(expectedZooList, actualZooList);
    }
}