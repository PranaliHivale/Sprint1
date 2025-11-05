package com.eventmanagement.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EventTable")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;

    private String eventName;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String eventType;

    @ManyToOne
    @JoinColumn(name = "orgId")
    private Organizer organizer;

    @ManyToOne
    @JoinColumn(name = "vId")
    private Venue venue;

    @ManyToMany
    @JoinTable(
            name = "Event_Attendee",
            joinColumns = @JoinColumn(name = "eventId"),
            inverseJoinColumns = @JoinColumn(name = "attId")
    )
    private List<Attendee> attendees = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "Event_Vendor",
            joinColumns = @JoinColumn(name = "eventId"),
            inverseJoinColumns = @JoinColumn(name = "vendId")
    )
    private List<Vendor> vendors = new ArrayList<>();

    // getters/setters
    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
    public LocalTime getEventTime() { return eventTime; }
    public void setEventTime(LocalTime eventTime) { this.eventTime = eventTime; }
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public Organizer getOrganizer() { return organizer; }
    public void setOrganizer(Organizer organizer) { this.organizer = organizer; }
    public Venue getVenue() { return venue; }
    public void setVenue(Venue venue) { this.venue = venue; }
    public List<Attendee> getAttendees() { return attendees; }
    public void setAttendees(List<Attendee> attendees) { this.attendees = attendees; }
    public List<Vendor> getVendors() { return vendors; }
    public void setVendors(List<Vendor> vendors) { this.vendors = vendors; }

    @Override
    public String toString() {
        return "Event{" + "eventId=" + eventId + ", eventName='" + eventName + '\'' + '}';
    }
}
