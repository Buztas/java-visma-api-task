package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MeetingService {

    public ArrayList<MeetingData> vismaMeetings = new ArrayList<>();

    public MeetingService()
    {
        loadMeetings();
    }

    public ArrayList<MeetingData> getMeetingData()
    {
        return vismaMeetings;
    }
    public void createNewMeeting(MeetingData meeting) {
        vismaMeetings.add(meeting);
        meeting.setId(vismaMeetings.size() - 1);
        System.out.println(vismaMeetings.size());
        saveMeetings();
    }

    public void deleteMeeting(int meetingId, Person person) {

    Logger logger = LoggerFactory.getLogger(MeetingService.class);

    person.setFirstName("Jane");
    person.setLastName("Doe");

    boolean exists = meetingId == (vismaMeetings.size() - 1);

        if(Objects.equals(vismaMeetings.get(meetingId).getResponsiblePerson(), person.getFullName()))
        {
            if(exists)
            {
                vismaMeetings.remove(meetingId);
                logger.info("Meeting successfully deleted.");
            }
            else
                throw new IllegalStateException(
                        "Meeting with id of " + meetingId + " does not exist."
                );
        } else
        {
            logger.error("Trying to delete meeting without permissions.");
        }

        saveMeetings();
    }

    public void saveMeetings()
    {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            File file = new File("meeting.json");
            objectMapper.writeValue(file, vismaMeetings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMeetings()
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();

            File file = new File("meeting.json");
            TypeReference<ArrayList<MeetingData>> typeRef = new TypeReference<ArrayList<MeetingData>>() {};
            vismaMeetings = objectMapper.readValue(file, typeRef);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addPersonToMeeting(Person person, int meetingId)
    {
        Logger logger = LoggerFactory.getLogger(MeetingService.class);

        MeetingData meeting = vismaMeetings.get(meetingId);

        for(int i = 0; i < meeting.getVismaEmployees().size(); i++)
        {
            if(Objects.equals(meeting.getVismaEmployees().get(i).getFullName(), person.getFullName()))
            {
                logger.warn("Person {} is already in the meeting with ID {}. Warning: Conflict!", person.getFullName(), meetingId);
                return;
            }
        }

        meeting.addVismaEmployees(person);
        person.setPersonId(meeting.getVismaEmployees().size() - 1);
        logger.info("Person {} has been added to meeting with ID {}.", person.getFullName(), meetingId);
        saveMeetings();
    }

    public void deletePersonFromMeeting(int meetingId, int personId) {
        if(meetingId >= 0 && meetingId <= vismaMeetings.size())
        {
            MeetingData meeting = vismaMeetings.get(meetingId);
            List<Person> vismaEmployees = meeting.getVismaEmployees();

            if(personId >= 0 && personId <= vismaEmployees.size())
            {
                Person person = vismaEmployees.get(personId);

                if(!Objects.equals(person.getFullName(),meeting.getResponsiblePerson()))
                {
                    vismaEmployees.remove(personId);
                    saveMeetings();
                    return;
                }
            }
        }
        throw new IllegalStateException("Person with id " + personId + " does not exist or you are trying to remove the responsible person.");
    }

    public List<MeetingData> filterByDescrption(String description) {
        return vismaMeetings.stream()
                .filter(meeting -> meeting.getDescription().contains(description))
                .collect(Collectors.toList());
    }

    public List<MeetingData> filterByResponsiblePerson(String responsiblePerson)
    {
        return vismaMeetings.stream()
                .filter(meeting -> meeting.getResponsiblePerson().equals(responsiblePerson))
                .collect(Collectors.toList());
    }

    public List<MeetingData> filterByCategory(String category)
    {
        return vismaMeetings.stream()
                .filter(meeting -> meeting.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<MeetingData> filterByType(String type)
    {
        return vismaMeetings.stream()
                .filter(meeting -> meeting.getType().equals(type))
                .collect(Collectors.toList());
    }

    public List<MeetingData> filterByDates(String from, String to)
    {
        List<MeetingData> filteredMeetings = new ArrayList<>();
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        for(int i = 0; i < vismaMeetings.size(); i++)
        {
            LocalDate startDate = LocalDate.parse(vismaMeetings.get(i).getStartDate());
            LocalDate endDate = LocalDate.parse(vismaMeetings.get(i).getEndDate());

            if(startDate.isAfter(fromDate) && endDate.isBefore(toDate))
                filteredMeetings.add(vismaMeetings.get(i));
        }
        return filteredMeetings;
    }

    public List<MeetingData> filterByMeetingSize(int size)
    {
        List<MeetingData> filteredMeetings = new ArrayList<>();
        for(int i = 0; i < vismaMeetings.size(); i++)
        {
            int temp = vismaMeetings.get(i).getVismaEmployees().size();
            if(temp >= size)
                filteredMeetings.add(vismaMeetings.get(i));
        }
        return filteredMeetings;
    }
}
