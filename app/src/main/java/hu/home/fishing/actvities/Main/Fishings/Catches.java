package hu.home.fishing.actvities.Main.Fishings;

public class Catches {
    //This class is handeling the Catches
    private String fishSpices;
    private Double weightOfCatch;
    private Integer lenghtOfCatch;

    public String getFishSpices() {
        return fishSpices;
    }

    public void setFishSpices(String fishSpices) {
        this.fishSpices = fishSpices;
    }

    public Double getWeightOfCatch() {
        return weightOfCatch;
    }

    public void setWeightOfCatch(Double weightOfCatch) {
        this.weightOfCatch = weightOfCatch;
    }

    public Integer getLenghtOfCatch() {
        return lenghtOfCatch;
    }

    public void setLenghtOfCatch(Integer lenghtOfCatch) {
        this.lenghtOfCatch = lenghtOfCatch;
    }

    public Catches(String fishSpices, Double weightOfCatch, Integer lenghtOfCatch) {
        this.fishSpices = fishSpices;
        this.weightOfCatch = weightOfCatch;
        this.lenghtOfCatch = lenghtOfCatch;
    }
}

