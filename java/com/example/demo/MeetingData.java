package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class MeetingData {
    private String name;
    private String responsiblePerson;
    private String description;
    private ArrayList<String> categoryList = new ArrayList<>(Arrays.asList("CodeMonkey", "Hub", "Short", "TeamBuilding"));;
    private ArrayList<String> typeList = new ArrayList<>(Arrays.asList("Live", "InPerson"));;
    private String category;
    private String type;
    private String startDate;
    private String endDate;
    private int meetingId;

    private ArrayList<Person> vismaEmployees = new ArrayList<>();

    public MeetingData()
    {

    }

    public MeetingData(String name, String responsiblePerson,
                       String description, String category, String type, String startDate,
                       String endDate) {
        this.name = name;
        this.responsiblePerson = responsiblePerson;
        this.description = description;

        if (categoryList.contains(category)) {
            this.category = category;
        }
        if (typeList.contains(type)) {
            this.type = type;
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return meetingId;
    }
    public void setId(int meetingId) {
        this.meetingId = meetingId;
    }

    public ArrayList<Person> getVismaEmployees()
    {
        return vismaEmployees;
    }

    public void addVismaEmployees(Person person)
    {
        vismaEmployees.add(person);
    }
}
