package com.example.starwarsapi.dto;

import com.example.starwarsapi.persistence.entity.Planet;
import com.example.starwarsapi.persistence.entity.Specie;

import java.util.Objects;

public class CharacterRequest {
    private int height;
    private int weight;
    private int age;
    private Planet planet;
    private Specie specie;
    private String name;

    public CharacterRequest(int height, int weight, int age, Planet planet, Specie specie, String name) {
        this.height = height;
        this.weight = weight;
        this.planet = planet;
        this.specie = specie;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet homeworld) {
        this.planet = homeworld;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CharacterRequest character = (CharacterRequest) o;
        return height == character.height &&
                weight == character.weight &&
                age == character.age &&
                Objects.equals(planet, character.planet) &&
                Objects.equals(specie, character.specie) &&
                Objects.equals(name, character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, weight, age, planet, specie, name);
    }

    @Override
    public String toString() {
        return "Character{" +
                "height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", planet=" + planet +
                ", specie=" + specie +
                ", name='" + name + '\'' +
                '}';
    }
}
