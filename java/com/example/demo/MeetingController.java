package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/meetings")
public class MeetingController {

    private MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService)
    {
        this.meetingService = meetingService;
    }

    @GetMapping()
    public ArrayList<MeetingData> getData()
    {
        return meetingService.getMeetingData();
    }

    @PostMapping(path = "/add-meeting")
    public void createNewMeeting(@RequestBody MeetingData meeting)
    {
        meetingService.createNewMeeting(meeting);
    }

    @DeleteMapping(path = "/delete-meeting/{meetingId}")
    public void deleteMeeting(@PathVariable("meetingId") int meetingId, Person person)
    {
        meetingService.deleteMeeting(meetingId, person);
    }

    @PostMapping(path = "/add-person")
    public void addPersonToMeeting(@RequestBody Person person, @RequestParam int meetingId)
    {
        meetingService.addPersonToMeeting(person, meetingId);
    }

    @DeleteMapping(path = "/delete-person/{meetingId}/{personId}")
    public void deletePersonFromMeeting(@PathVariable("meetingId") int meetingId, @PathVariable("personId") int personId)
    {
        meetingService.deletePersonFromMeeting(meetingId, personId);
    }

    @GetMapping(path = "/filter/description")
    public List<MeetingData> filterByDescription(@RequestParam("description") String description)
    {
        return meetingService.filterByDescrption(description);
    }

    @GetMapping(path = "/filter/responsiblePerson")
    public List<MeetingData> filterByResponsiblePerson(@RequestParam("responsiblePerson") String responsiblePerson)
    {
        return meetingService.filterByResponsiblePerson(responsiblePerson);
    }

    @GetMapping(path = "/filter/category")
    public List<MeetingData> filterByCategory(@RequestParam("category") String category)
    {
        return meetingService.filterByCategory(category);
    }

    @GetMapping(path = "/filter/type")
    public List<MeetingData> filterByType(@RequestParam("type") String type)
    {
        return meetingService.filterByType(type);
    }

    @GetMapping(path = "/filter/start")
    public List<MeetingData> filterByDates(@RequestParam("start") String from, @RequestParam("end") String to)
    {
        return meetingService.filterByDates(from, to);
    }

    @GetMapping(path = "/filter/size")
    public List<MeetingData> filterByMeetingSize(@RequestParam("size") int size)
    {
        return meetingService.filterByMeetingSize(size);
    }
}
