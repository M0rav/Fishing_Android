package hu.home.fishing.actvities.Main.Map;

public class Locations {
    private Float xCordinate;
    private Float yCordinate;
    private String comment;

    public Locations(Float xCordinate, Float yCordinate, String comment) {
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
        this.comment = comment;
    }

    public Float getxCordinate() {
        return xCordinate;
    }

    public void setxCordinate(Float xCordinate) {
        this.xCordinate = xCordinate;
    }

    public Float getyCordinate() {
        return yCordinate;
    }

    public void setyCordinate(Float yCordinate) {
        this.yCordinate = yCordinate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
