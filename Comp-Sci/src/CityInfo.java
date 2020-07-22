/*
 * Duy Nguyen
 * CityInfo.java
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;

class City {

    private String country;
    private String name;
    private int population;

    public City(String country, String name, int population) {
        this.country = country;
        this.name = name;
        this.population = population;
    }

    // Getters/Setters
    public String getCountry() {
        return country;
    }
    public String getName() {
        return name;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return  country + "; " + name + "; " + population;
    }

}

public class CityInfo {

    public static ArrayList<City> readCityFile(String fileName) {
        return null;
    }


    public static void main(String[] args) {
        City sanJose = new City("US", "San Jose", 1000000);
        System.out.println(sanJose);
    }

}
