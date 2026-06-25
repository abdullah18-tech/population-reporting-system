    package com.ali;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        Database db = new Database();

        db.connect();

//        ArrayList<Country> countries = db.getCountries();
//
//        for(Country c : countries)
//        {
//            System.out.printf(
//                    "%s | %s | %s | %s | %d | %d%n",
//                    c.code,
//                    c.name,
//                    c.continent,
//                    c.region,
//                    c.population,
//                    c.capital
//            );
//        }

//        ArrayList<City> cities = db.getCities();
//
//        for(City city : cities)
//        {
//            System.out.printf(
//                    "%s | %s | %s | %d%n",
//                    city.name,
//                    city.country,
//                    city.district,
//                    city.population
//            );
//        }

        ArrayList<CapitalCity> capitals = db.getCapitalCities();

//        for(CapitalCity capital : capitals)
//        {
//            System.out.printf(
//                    "%s | %s | %d%n",
//                    capital.name,
//                    capital.country,
//                    capital.population
//            );
//        }

        ArrayList<City> cities = db.getTopNCities(10);

//        for(City city : cities)
//        {
//            System.out.printf(
//                    "%s | %s | %s | %d%n",
//                    city.name,
//                    city.country,
//                    city.district,
//                    city.population
//            );
//        }

//        long population = db.getWorldPopulation();
//
//        System.out.println("World Population: " + population);

//        System.out.println(
//                "Asia Population: " +
//                        db.getContinentPopulation("Asia")
//        );

//        System.out.println(
//                "Southern and Central Asia Population: " +
//                        db.getRegionPopulation("Southern and Central Asia")
//        );

        System.out.println(
                "Pakistan Population: " +
                        db.getCountryPopulation("Pakistan")
        );
    }
}