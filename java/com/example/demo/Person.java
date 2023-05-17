package com.example.demo;

public class Person {

    private String firstName;
    private String lastName;
    private int personId;

    private String fullName;

    public Person()
    {

    }

    public Person(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int meetingId) {
        this.personId = meetingId;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }
}
