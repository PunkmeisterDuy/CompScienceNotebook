/*
 * Duy Nguyen
 * CityInfo.java
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
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

    public static ArrayList<City> readCityFile(File fileName) throws FileNotFoundException {

        Scanner file = new Scanner(fileName);
        ArrayList<City> cityList = new ArrayList<>();

        while (file.hasNextLine()) {

            System.out.println(file.nextLine());
            String line = file.nextLine();
            String [] properties = line.split(";");

            if (properties.length != 3) {
                System.out.printf("\"%s\" does not have three entries.\n",
                        line);
            }
            else {
                try {
                    City city = new City(properties[0], properties[1], Integer.parseInt(properties[2]));
                }
                catch (InputMismatchException ex) {
                    System.out.println("Inputs are not correct data type");
                }
            }
            //Scanner input = new Scanner(file.nextLine());
            //for (int strings = 0; strings < 2; strings++) {
            //    City city = new City(file.next(), file.next(), file.nextInt());
            //    cityList.add(city);
            //}
        }
        return cityList;

    }


    public static void main(String[] args) throws FileNotFoundException {
        City sanJose = new City("US", "San Jose", 1000000);
        System.out.println(sanJose);

        File cities = new File("citylist.dat");
        readCityFile(cities);
        System.out.println();
    }

}
