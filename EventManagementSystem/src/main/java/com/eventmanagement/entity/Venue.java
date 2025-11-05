package com.eventmanagement.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vId;

    private String vName;
    private String vAddress;
    private int vCapacity;
    private String vContact;
    private String vOwner;

    @OneToMany(mappedBy = "venue")
    private List<Event> events = new ArrayList<>();

    // getters/setters
    public int getVId() { return vId; }
    public void setVId(int vId) { this.vId = vId; }
    public String getVName() { return vName; }
    public void setVName(String vName) { this.vName = vName; }
    public String getVAddress() { return vAddress; }
    public void setVAddress(String vAddress) { this.vAddress = vAddress; }
    public int getVCapacity() { return vCapacity; }
    public void setVCapacity(int vCapacity) { this.vCapacity = vCapacity; }
    public String getVContact() { return vContact; }
    public void setVContact(String vContact) { this.vContact = vContact; }
    public String getVOwner() { return vOwner; }
    public void setVOwner(String vOwner) { this.vOwner = vOwner; }
    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }

    @Override
    public String toString() {
        return "Venue{" + "vId=" + vId + ", vName='" + vName + '\'' + '}';
    }
}
