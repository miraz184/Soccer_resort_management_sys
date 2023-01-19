package com.glo.service;

import com.glo.entity.Guest;
import com.glo.enums.Country;
import com.glo.enums.Hobby;
import com.glo.enums.Language;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EventManagerService {
    private static Scanner scanner = null;


    public List<Guest> populateGuests(){

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoCollection<Document> collection = mongoClient.getDatabase("DemoDB").getCollection("guest");
        scanner = new Scanner(System.in);

        System.out.println("Enter number of guests you want to read : ");
        int numberOfGuests = scanner.nextInt();

        int i = 1;
        while (i <= numberOfGuests) {

            Guest guest = new Guest();
            List<Hobby> hobbies = new ArrayList<>();

            System.out.println("Enter name of guest" + i + " :");
            String name = scanner.next();

            System.out.println("Enter dob of guest" + i + " :");
            String dob = scanner.next();

            System.out.println("Enter country of guest" + i + " :");
            String country = scanner.next();

            System.out.println("Enter language of guest" + i + " :");
            String language = scanner.next();

            System.out.println("Enter number of hobbies" + i + " : ");
            int numberOfHobbies = scanner.nextInt();

            int j = 1;
            while (j <= numberOfHobbies) {

                System.out.println("Enter hobby" + j + " : ");
                String hobby = scanner.next();
                hobbies.add(Hobby.valueOf(hobby));
                j++;
            }

            guest.setName(name);
            guest.setDateOfBirth(dob);
            guest.setLanguage(Language.valueOf(language));
            guest.setCountry(Country.valueOf(country));
            guest.setHobbies(hobbies);

            // converting java object to json and json to MongoDB document
            Gson gson = new Gson();
            Document document = Document.parse(gson.toJson(guest));

            // saving document to database
            collection.insertOne(document);

            i++;
        }

        //getting documents from mongodb and converting to list
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> guests = documents.iterator();
        List<Guest> guestList = new ArrayList<>();
        Gson gson = new Gson();
        while (guests.hasNext()) {
            String json = guests.next().toJson();
            Guest guest = gson.fromJson(json, Guest.class);
            guestList.add(guest);
        }

        mongoClient.close();
        scanner.close();

        return guestList;
    }
    /**
     * To get list of guests by country
     */
    public List<Guest> filterGuestsByCountry(String country, List<Guest> guests) {

        return guests.stream().filter(guest -> guest.getCountry().equals(Country.valueOf(country)))
                .collect(Collectors.toList());

    }

    /**
     * To get list of guests who are from spain and who love music, dance
     */
    public List<Guest> filterGuestsBySpainWhoLoveMusicAndDance(List<Guest> guests) {

        return guests.stream().filter(guest -> guest.getCountry().equals(Country.valueOf("Spain")))
                .filter(guest -> guest.getHobbies().contains(Hobby.valueOf("Dance"))
                        && guest.getHobbies().contains(Hobby.valueOf("Music")))
                .collect(Collectors.toList());
    }

    /**
     * To get list of guests who are from spain and who love drink and age is above
     * 18
     */
    public List<Guest> filterGuestBySpainWhoLoveDrinkAndAgeAbove18(List<Guest> guests) {

        return guests.stream().filter(guest -> guest.getCountry().equals(Country.valueOf("Spain")))
                .filter(guest -> guest.getHobbies().contains(Hobby.valueOf("Drink"))
                        && calculateAge(LocalDate.parse(String.valueOf(guest.getDateOfBirth()))) > 18)
                .collect(Collectors.toList());
    }

    /**
     * To get list of guests who are from France and who love sports
     */
    public List<Guest> filterGuestByFranceWhoLoveSports(List<Guest> guests) {

        return guests.stream().filter(guest -> guest.getCountry().equals(Country.valueOf("France"))
                && guest.getHobbies().contains(Hobby.valueOf("WaterSports"))).collect(Collectors.toList());
    }

    /**
     * To get number of guests whose age is above 70
     */
    public  long guestsWhoAgeIsAbove70(List<Guest> guests) {

        return guests.stream()
                .filter(guest -> calculateAge(LocalDate.parse(String.valueOf(guest.getDateOfBirth()))) > 70).count();
    }

    /**
     * To get list of guests who speak chinese and who love to read
     */
    public  List<Guest> filterGuestByChineseAndWhoLoveToRead(List<Guest> guests) {

        return guests.stream().filter(guest -> guest.getLanguage().equals(Language.valueOf("Chinese"))
                && guest.getHobbies().contains(Hobby.valueOf("Read"))).collect(Collectors.toList());
    }

    /**
     * Method to calculate age
     */
    public  int calculateAge(LocalDate birthDate) {

        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}