package com.abdullah;

public class Country {

    public String code;
    public String name;
    public String continent;
    public String region;
    public int population;
    public int capital;

    public Country(String code, String name, String continent,
                   String region, int population, int capital) {

        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }
}