package com.spring.springboot.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySources;

/**
 * @author Frankie Yang on 2019-10-15.
 */
@ConfigurationProperties(prefix = "ppp")
public class PersonProp {

    private String name = "AAAAAAA";
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }
    public String getInfor() {
        return infor;
    }

}
