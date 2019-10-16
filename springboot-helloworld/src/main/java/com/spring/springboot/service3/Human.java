package com.spring.springboot.service3;

/**
 * @author Frankie Yang on 2019-10-16.
 */
public class Human {

    private Long id;
    private String username;
    private String address;
    //省略 getter/setter


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

