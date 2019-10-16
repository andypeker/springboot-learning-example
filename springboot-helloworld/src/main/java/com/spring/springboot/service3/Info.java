package com.spring.springboot.service3;

/**
 * @author Frankie Yang on 2019-10-16.
 */
public class Info {
    private String addr;
    private int income;
    private String nationality;
    private String gender;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getIncome() {
        return income;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
