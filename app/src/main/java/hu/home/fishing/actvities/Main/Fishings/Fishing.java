package hu.home.fishing.actvities.Main.Fishings;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class Fishing {
    private String species;
    private String weight;
    private  String length;
    private String location;

    public Fishing(String species, String weight, String length, String location) {
        this.species = species;
        this.weight = weight;
        this.length = length;
        this.location = location;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
