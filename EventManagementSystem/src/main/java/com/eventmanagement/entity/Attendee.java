package com.eventmanagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attId;

    private String attName;
    private String attEmail;
    private String attPhone;
    private String attAddress;

    @ManyToMany(mappedBy = "attendees")
    private List<Event> events = new ArrayList<>();

    // getters/setters
    public int getAttId() { return attId; }
    public void setAttId(int attId) { this.attId = attId; }
    public String getAttName() { return attName; }
    public void setAttName(String attName) { this.attName = attName; }
    public String getAttEmail() { return attEmail; }
    public void setAttEmail(String attEmail) { this.attEmail = attEmail; }
    public String getAttPhone() { return attPhone; }
    public void setAttPhone(String attPhone) { this.attPhone = attPhone; }
    public String getAttAddress() { return attAddress; }
    public void setAttAddress(String attAddress) { this.attAddress = attAddress; }
    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }

    @Override
    public String toString() {
        return "Attendee{" + "attId=" + attId + ", attName='" + attName + '\'' + '}';
    }
}
