package com.glo;

import com.glo.entity.Guest;
import com.glo.service.EventManagerService;

import java.util.List;

public class EventManagerMain {
    private static List<Guest> guests = null;
    public static void main(String[] args) {
//        List<Guest> guests = null;
//        EventManagerService service = new EventManagerService();
//        guests = service.populateGuests();
//        System.out.println(guests);
//
//        System.out.println("-------------------Spain Love Drink---------------------");
//        System.out.println(service.filterGuestFromSpainWholovesDrinkAndAboveEighteen(guests));
//        System.out.println("-------------------Spain Love DANCE and MUSIC---------------------");
//        System.out.println(service.filterGuestFromSpainWholovesToDanceAndMusic(guests));
//        System.out.println("-------------------France Love SPORTS---------------------");
//        System.out.println(service.filterGuestFromFranceAndLoveSports(guests));
//
//        System.out.println("==================above 70===========");
//        System.out.println(service.findNumberGuestsAboveSeventy(guests));
//        System.out.println("==================CHINISE and EAT==========");
//        System.out.println(service.findGuestsWhoSpeakChineseAndLoveToEat(guests));

        EventManagerService service=new EventManagerService();

        guests = service.populateGuests();

        System.out.println("\n\nGuests from France : ");
        List<Guest> guestsByCountry = service.filterGuestsByCountry("France", guests);
        guestsByCountry.forEach(System.out::println);

        System.out.println("\n\nGuests who are from Spain and Who love music and dance : ");
        List<Guest> guestsFromSpain = service.filterGuestsBySpainWhoLoveMusicAndDance(guests);
        guestsFromSpain.forEach(System.out::println);

        System.out.println("\n\nGuests who are from Spain and Who love drink and age above 18 : ");
        List<Guest> guestsFromSpain1 = service.filterGuestBySpainWhoLoveDrinkAndAgeAbove18(guests);
        guestsFromSpain1.forEach(System.out::println);

        System.out.println("\n\nGuests who are from France and Who love sports : ");
        List<Guest> guestsFromFrance = service.filterGuestByFranceWhoLoveSports(guests);
        guestsFromFrance.forEach(System.out::println);

        System.out.println("\n\nGuests who age is above 70 : " + service.guestsWhoAgeIsAbove70(guests));

        System.out.println("\n\nGuests who speak chinese and Who love to read : ");
        List<Guest> guestsFromChina = service.filterGuestByChineseAndWhoLoveToRead(guests);
        guestsFromChina.forEach(System.out::println);

    }
}