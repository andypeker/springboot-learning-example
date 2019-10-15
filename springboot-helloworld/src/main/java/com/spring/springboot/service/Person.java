package com.spring.springboot.service;

/**
 * @author Frankie Yang on 2019-10-15.
 */
public class Person {
    private String name;
    private int age;
    private String gender;
    private String id;
    private int income;
    private String infor;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIncome() {
        return income;
    }

    public String getId() {
        return id;
    }

    public String getInfor() {
        return infor;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String introducePerson() {
        return this.name + "\t" +
                this.age + "\t" +
                this.gender + "\t" +
                this.id + "\t" +
                this.income + "\t" +
                this.infor;
    }
}
