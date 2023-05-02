package hu.home.fishing.actvities.Main.Map;

public class Locations {
    private Float xLoccord;
    private Float yLoccord;
    private String comment;

    public Locations(Float xLoccord, Float yLoccord, String comment) {
        this.xLoccord = xLoccord;
        this.yLoccord = yLoccord;
        this.comment = comment;
    }

    public Float getxLoccord() {
        return xLoccord;
    }

    public void setxLoccord(Float xLoccord) {
        this.xLoccord = xLoccord;
    }

    public Float getyLoccord() {
        return yLoccord;
    }

    public void setyLoccord(Float yLoccord) {
        this.yLoccord = yLoccord;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
