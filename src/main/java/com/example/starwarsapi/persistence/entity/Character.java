package com.example.starwarsapi.persistence.entity;

import java.util.Objects;

public class Character {
    private Integer id;
    private int height;
    private int weight;
    private int age;
    private Planet planet;
    private Specie specie;
    private String name;

    public Character(Integer id, int height, int weight, int age, Planet planet, Specie specie, String name) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.planet = planet;
        this.specie = specie;
        this.name = name;
        this.age = age;
    }

    public Character() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        Character character = (Character) o;
        return height == character.height &&
                weight == character.weight &&
                age == character.age &&
                Objects.equals(id, character.id) &&
                Objects.equals(planet, character.planet) &&
                Objects.equals(specie, character.specie) &&
                Objects.equals(name, character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, height, weight, age, planet, specie, name);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", planet=" + planet +
                ", specie=" + specie +
                ", name='" + name + '\'' +
                '}';
    }
}

