package com.eventmanagement.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vendId;

    private String vendName;
    private String vendService;
    private String vendContact;
    private String vendEmail;
    private String vendAddress;

    @ManyToMany(mappedBy = "vendors")
    private List<Event> events = new ArrayList<>();

    // getters/setters
    public int getVendId() { return vendId; }
    public void setVendId(int vendId) { this.vendId = vendId; }
    public String getVendName() { return vendName; }
    public void setVendName(String vendName) { this.vendName = vendName; }
    public String getVendService() { return vendService; }
    public void setVendService(String vendService) { this.vendService = vendService; }
    public String getVendContact() { return vendContact; }
    public void setVendContact(String vendContact) { this.vendContact = vendContact; }
    public String getVendEmail() { return vendEmail; }
    public void setVendEmail(String vendEmail) { this.vendEmail = vendEmail; }
    public String getVendAddress() { return vendAddress; }
    public void setVendAddress(String vendAddress) { this.vendAddress = vendAddress; }
    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }

    @Override
    public String toString() {
        return "Vendor{" + "vendId=" + vendId + ", vendName='" + vendName + '\'' + '}';
    }
}
