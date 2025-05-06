package com.example.starwarsapi.persistence.entity;

import java.util.Objects;

public class Planet {
    private int diameter;
    private String climate;
    private String name;
    private String terrain;

    public Planet(int diameter, String climate, String name, String terrain) {
        this.diameter = diameter;
        this.climate = climate;
        this.name = name;
        this.terrain = terrain;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return diameter == planet.diameter &&
                Objects.equals(climate, planet.climate) &&
                Objects.equals(name, planet.name) &&
                Objects.equals(terrain, planet.terrain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diameter, climate, name, terrain);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "diameter=" + diameter +
                ", climate='" + climate + '\'' +
                ", name='" + name + '\'' +
                ", terrain='" + terrain + '\'' +
                '}';
    }
}

