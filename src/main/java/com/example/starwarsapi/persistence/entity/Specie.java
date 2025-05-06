package com.example.starwarsapi.persistence.entity;

import java.util.Objects;

public class Specie {
    private int averageHeight;
    private String language;
    private String name;

    public Specie(int averageHeight, String language, String name) {
        this.averageHeight = averageHeight;
        this.language = language;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(int averageHeight) {
        this.averageHeight = averageHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Specie specie = (Specie) o;
        return averageHeight == specie.averageHeight &&
                Objects.equals(language, specie.language) &&
                Objects.equals(name, specie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageHeight, language, name);
    }

    @Override
    public String toString() {
        return "Specie{" +
                "averageHeight=" + averageHeight +
                ", language='" + language + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

