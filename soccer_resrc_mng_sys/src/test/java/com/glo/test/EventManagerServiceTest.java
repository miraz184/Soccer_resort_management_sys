package com.glo.test;

import com.glo.entity.Guest;
import com.glo.enums.Country;
import com.glo.enums.Hobby;
import com.glo.enums.Language;
import com.glo.service.EventManagerService;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EventManagerServiceTest {
    private List<Guest> guests = null;
    private EventManagerService service = null;

    @BeforeEach
    void setUp() throws Exception {

        service = new EventManagerService();

        guests = new ArrayList<>();

        guests.add(new Guest("Kumar", "1995-05-20", Country.valueOf("USA"), Language.valueOf("English"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Music"))));

        guests.add(new Guest("Nandish", "1950-05-20", Country.valueOf("Spain"), Language.valueOf("Spanish"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Music"), Hobby.valueOf("Dance"))));

        guests.add(new Guest("Naresh", "1948-05-20", Country.valueOf("Spain"), Language.valueOf("Spanish"),
                Arrays.asList(Hobby.valueOf("Music"), Hobby.valueOf("Dance"), Hobby.valueOf("Drink"))));

        guests.add(new Guest("Shravan", "1945-05-20", Country.valueOf("France"), Language.valueOf("French"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Music"))));

        guests.add(new Guest("Naveen", "1995-05-20", Country.valueOf("France"), Language.valueOf("French"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Read"))));

        guests.add(new Guest("Girish", "1995-05-20", Country.valueOf("China"), Language.valueOf("Chinese"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Read"))));

        guests.add(new Guest("Harshith", "1995-05-20", Country.valueOf("China"), Language.valueOf("Chinese"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Read"))));
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testPopulateGuests() {

    }

    @Test
    void testFilterGuestsByCountry() {

        String country = "France";
        List<Guest> expected = new ArrayList<>();
        expected.add(new Guest("Shravan", "1945-05-20", Country.valueOf("France"), Language.valueOf("French"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Music"))));
        expected.add(new Guest("Naveen", "1995-05-20", Country.valueOf("France"), Language.valueOf("French"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Read"))));
        assertEquals(expected.toString(), service.filterGuestsByCountry(country, guests).toString());
    }


    @Test
    void testFilterGuestsBySpainWhoLoveMusicAndDance() {

        List<Guest> expected = new ArrayList<>();
        expected.add(new Guest("Nandish", "1950-05-20", Country.valueOf("Spain"), Language.valueOf("Spanish"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Music"), Hobby.valueOf("Dance"))));
        expected.add(new Guest("Naresh", "1948-05-20", Country.valueOf("Spain"), Language.valueOf("Spanish"),
                Arrays.asList(Hobby.valueOf("Music"), Hobby.valueOf("Dance"), Hobby.valueOf("Drink"))));
        assertEquals(expected.toString(), service.filterGuestsBySpainWhoLoveMusicAndDance(guests).toString());
    }


    @Test
    void testFilterGuestBySpainWhoLoveDrinkAndAgeAbove18() {

        List<Guest> expected = new ArrayList<>();
        expected.add(new Guest("Naresh", "1948-05-20", Country.valueOf("Spain"), Language.valueOf("Spanish"),
                Arrays.asList(Hobby.valueOf("Music"), Hobby.valueOf("Dance"), Hobby.valueOf("Drink"))));
        assertEquals(expected.toString(), service.filterGuestBySpainWhoLoveDrinkAndAgeAbove18(expected).toString());
    }


    @Test
    void testFilterGuestByFranceWhoLoveSports() {

        List<Guest> expected = new ArrayList<>();
        expected.add(new Guest("Shravan", "1945-05-20", Country.valueOf("France"), Language.valueOf("French"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Music"))));
        expected.add(new Guest("Naveen", "1995-05-20", Country.valueOf("France"), Language.valueOf("French"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Read"))));
        assertEquals(expected.toString(), service.filterGuestByFranceWhoLoveSports(expected).toString());
    }


    @Test
    void testGuestsWhoAgeIsAbove70() {

        List<Guest> expected = new ArrayList<>();
        expected.add(new Guest("Nandish", "1950-05-20", Country.valueOf("Spain"), Language.valueOf("Spanish"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Music"), Hobby.valueOf("Dance"))));

        expected.add(new Guest("Naresh", "1948-05-20", Country.valueOf("Spain"), Language.valueOf("Spanish"),
                Arrays.asList(Hobby.valueOf("Music"), Hobby.valueOf("Dance"), Hobby.valueOf("Drink"))));

        expected.add(new Guest("Shravan", "1945-05-20", Country.valueOf("France"), Language.valueOf("French"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Music"))));
        assertEquals(3, service.guestsWhoAgeIsAbove70(guests));
    }


    @Test
    void testFilterGuestByChineseAndWhoLoveToRead() {

        List<Guest> expected = new ArrayList<>();
        expected.add(new Guest("Girish", "1995-05-20", Country.valueOf("China"), Language.valueOf("Chinese"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Read"))));
        expected.add(new Guest("Harshith", "1995-05-20", Country.valueOf("China"), Language.valueOf("Chinese"),
                Arrays.asList(Hobby.valueOf("WaterSports"), Hobby.valueOf("Read"))));
        assertEquals(expected.toString(), service.filterGuestByChineseAndWhoLoveToRead(guests).toString());
    }


    @Test
    void testCalculateAge() {

        assertEquals(24, service.calculateAge(LocalDate.parse(String.valueOf("1998-05-20"))));
    }
}
