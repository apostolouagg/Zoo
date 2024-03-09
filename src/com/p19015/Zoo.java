package com.p19015;

import java.io.Serializable;

public class Zoo implements Serializable {
    private int animalcode;
    private String name;
    private String type;
    private double weight; //Σε κιλά
    private int age; //Σε χρόνια

    public int getAnimalcode() {
        return animalcode;
    }

    public void setAnimalcode(int animalcode) {
        this.animalcode = animalcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
