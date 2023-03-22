package hu.home.fishing;

import java.text.DateFormat;
import java.util.Date;

public class Fishing {
    private String title;
    private String startOfFishing;
    private Integer piecesOfFishes;
    private Double weightOfTheFishes;
    private String placeOfTheFishing;


    public Fishing(String title, String startOfFishing, Integer piecesOfFishes, Double weightOfTheFishes, String placeOfTheFishing) {
        this.title = title;
        this.startOfFishing = startOfFishing;
        this.piecesOfFishes = piecesOfFishes;
        this.weightOfTheFishes = weightOfTheFishes;
        this.placeOfTheFishing = placeOfTheFishing;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartOfFishing() {
        return startOfFishing;
    }

    public void setStartOfFishing(String startOfFishing) {
        this.startOfFishing = startOfFishing;
    }

    public Integer getPiecesOfFishes() {
        return piecesOfFishes;
    }

    public void setPiecesOfFishes(Integer piecesOfFishes) {
        this.piecesOfFishes = piecesOfFishes;
    }

    public Double getWeightOfTheFishes() {
        return weightOfTheFishes;
    }

    public void setWeightOfTheFishes(Double weightOfTheFishes) {
        this.weightOfTheFishes = weightOfTheFishes;
    }

    public String getPlaceOfTheFishing() {
        return placeOfTheFishing;
    }

    public void setPlaceOfTheFishing(String placeOfTheFishing) {
        this.placeOfTheFishing = placeOfTheFishing;
    }
}
