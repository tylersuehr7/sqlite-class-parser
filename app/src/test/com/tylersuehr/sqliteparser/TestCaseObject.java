package com.tylersuehr.sqliteparser;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 */
class TestCaseObject {
    private int id;
    private String name;
    private String email;

    @IGNORE
    private double money;


    public TestCaseObject() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}