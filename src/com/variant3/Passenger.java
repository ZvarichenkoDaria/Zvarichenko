package com.variant3;

public class Passenger {

    private Long id;

    private Boolean survived;

    private Short pClass;

    private String name;

    private String sex;

    private Float age;

    private Integer sibSp;

    private Integer parch;

    private String ticket;

    private Float fare;

    private String cabin;

    private Character embarked;

    public Passenger(Long id, Boolean survived, Short pClass, String name, String sex, Float age, Integer sibSp, Integer parch, String ticket, Float fare, String cabin, Character embarked) {
        this.id = id;
        this.survived = survived;
        this.pClass = pClass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.sibSp = sibSp;
        this.parch = parch;
        this.ticket = ticket;
        this.fare = fare;
        this.cabin = cabin;
        this.embarked = embarked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getSurvived() {
        return survived;
    }

    public void setSurvived(Boolean survived) {
        this.survived = survived;
    }

    public Short getpClass() {
        return pClass;
    }

    public void setpClass(Short pClass) {
        this.pClass = pClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Float getAge() {
        return age;
    }

    public void setAge(Float age) {
        this.age = age;
    }

    public Integer getSibSp() {
        return sibSp;
    }

    public void setSibSp(Integer sibSp) {
        this.sibSp = sibSp;
    }

    public Integer getParch() {
        return parch;
    }

    public void setParch(Integer parch) {
        this.parch = parch;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Float getFare() {
        return fare;
    }

    public void setFare(Float fare) {
        this.fare = fare;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public Character getEmbarked() {
        return embarked;
    }

    public void setEmbarked(Character embarked) {
        this.embarked = embarked;
    }
}