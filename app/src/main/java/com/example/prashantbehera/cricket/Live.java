package com.example.prashantbehera.cricket;

/**
 * Created by Prashant Behera on 28-Oct-17.
 */

public class Live {
    public String Ground;
    public String Date;
    public String Format;
    public String Time, id;
    public String marketPrice;
    private String imageTeam1;
    private String imageTeam2;
    private String Team1;
    private String Team2;

    public Live() {
    }


    public Live(String imageTeam1, String imageTeam2, String Team1, String Team2, String marketPrice, String Ground, String id, String Date, String Format, String Time) {
        this.imageTeam1 = imageTeam1;
        this.imageTeam2 = imageTeam2;
        this.Team1 = Team1;
        this.Team2 = Team2;
        this.Time = Time;
        this.Ground = Ground;
        this.Date = Date;
        this.Format = Format;
        this.id = id;
        this.marketPrice = marketPrice;
    }

    public String getImageTeam1() {
        return imageTeam1;
    }

    public void setImageTeam1(String imageTeam1) {
        this.imageTeam1 = imageTeam1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getImageTeam2() {
        return imageTeam2;
    }

    public void setImageTeam2(String imageTeam2) {
        this.imageTeam2 = imageTeam2;
    }

    public String getTeam1() {
        return Team1;
    }

    public void setTeam1(String team1) {
        Team1 = team1;
    }

    public String getTeam2() {
        return Team2;
    }

    public void setTeam2(String team2) {
        Team2 = team2;
    }

    public String getGround() {
        return Ground;
    }

    public void setGround(String ground) {
        Ground = ground;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}

