package com.abdullah;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    private Connection con;

    public void connect() {

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/world",
                    "debian-sys-maint",
                    "EmnK2fZORuSTpdPo"
            );

            System.out.println("Database Connected!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Country> getCountries()
    {
        ArrayList<Country> countries = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT Code, Name, Continent, Region, Population, Capital " +
                            "FROM country ORDER BY Population DESC";

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                Country country = new Country(
                        rs.getString("Code"),
                        rs.getString("Name"),
                        rs.getString("Continent"),
                        rs.getString("Region"),
                        rs.getInt("Population"),
                        rs.getInt("Capital")
                );

                countries.add(country);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return countries;
    }

    public ArrayList<City> getCities()
    {
        ArrayList<City> cities = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT city.Name, " +
                            "country.Name AS Country, " +
                            "city.District, " +
                            "city.Population " +
                            "FROM city " +
                            "JOIN country " +
                            "ON city.CountryCode = country.Code " +
                            "ORDER BY city.Population DESC";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                City city = new City(
                        rs.getString("Name"),
                        rs.getString("Country"),
                        rs.getString("District"),
                        rs.getInt("Population")
                );

                cities.add(city);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return cities;
    }

    public ArrayList<CapitalCity> getCapitalCities()
    {
        ArrayList<CapitalCity> capitals = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT city.Name, " +
                            "country.Name AS Country, " +
                            "city.Population " +
                            "FROM city " +
                            "JOIN country ON city.ID = country.Capital " +
                            "ORDER BY city.Population DESC";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                capitals.add(
                        new CapitalCity(
                                rs.getString("Name"),
                                rs.getString("Country"),
                                rs.getInt("Population")
                        )
                );
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return capitals;
    }

    public ArrayList<City> getTopNCities(int n)
    {
        ArrayList<City> cities = new ArrayList<>();

        try
        {
            String sql =
                    "SELECT city.Name, " +
                            "country.Name AS Country, " +
                            "city.District, " +
                            "city.Population " +
                            "FROM city " +
                            "JOIN country ON city.CountryCode = country.Code " +
                            "ORDER BY city.Population DESC " +
                            "LIMIT ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, n);

            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                cities.add(
                        new City(
                                rs.getString("Name"),
                                rs.getString("Country"),
                                rs.getString("District"),
                                rs.getInt("Population")
                        )
                );
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return cities;
    }


    public long getWorldPopulation()
    {
        long population = 0;

        try
        {
            String sql = "SELECT SUM(Population) AS TotalPopulation FROM country";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
            {
                population = rs.getLong("TotalPopulation");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return population;
    }

    public long getContinentPopulation(String continent)
    {
        long population = 0;

        try
        {
            String sql =
                    "SELECT SUM(Population) AS TotalPopulation " +
                            "FROM country " +
                            "WHERE Continent = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, continent);

            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                population = rs.getLong("TotalPopulation");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return population;
    }


    public long getRegionPopulation(String region)
    {
        long population = 0;

        try
        {
            String sql =
                    "SELECT SUM(Population) AS TotalPopulation " +
                            "FROM country " +
                            "WHERE Region = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, region);

            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                population = rs.getLong("TotalPopulation");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return population;
    }


    public long getCountryPopulation(String country)
    {
        long population = 0;

        try
        {
            String sql =
                    "SELECT Population " +
                            "FROM country " +
                            "WHERE Name = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, country);

            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                population = rs.getLong("Population");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return population;
    }
}