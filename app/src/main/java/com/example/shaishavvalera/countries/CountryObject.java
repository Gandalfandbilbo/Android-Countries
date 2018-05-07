package com.example.shaishavvalera.countries;

/**
 * Created by Shaishav Valera on 24-Oct-17.
 */

public class CountryObject
{
    public CountryObject(String name, String capital, String continent, Double latitude, Double longitude, Double area, String currency, Double population, String flagcode)
    {
        this.name = name;
        this.capital = capital;
        this.continent = continent;
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
        this.currency = currency;
        this.population = population;
        this.flagcode = flagcode;
    }
    public String getName() {
        return name;
    }

    public void setCountry_name(String name) {
        this.name = name;
    }

    public String getCapital() { return capital; }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public String getFlagcode() {
        return flagcode;
    }

    public void setFlagcode(String flagcode) {
        this.flagcode = flagcode;
    }

    private String name;
    private String capital;
    private String continent;
    private Double latitude;
    private Double longitude;
    private Double area;
    private String currency;
    private Double population;
    private String flagcode;
}
