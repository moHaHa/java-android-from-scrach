package com.example.javaemptyactivity.models;

public class Agent {

    private  int id;
    private String name;
    private String number;
    private String description;
    public Agent(int id ,String name, String number, String description) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public  void setId ( int id ) {
        this.id = id ;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
