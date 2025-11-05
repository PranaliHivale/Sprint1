package com.eventmanagement.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orgId;

    private String orgName;
    private String orgAddress;
    private String orgPhone;
    private String orgEmail;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    // getters & setters
    public int getOrgId() { return orgId; }
    public void setOrgId(int orgId) { this.orgId = orgId; }
    public String getOrgName() { return orgName; }
    public void setOrgName(String orgName) { this.orgName = orgName; }
    public String getOrgAddress() { return orgAddress; }
    public void setOrgAddress(String orgAddress) { this.orgAddress = orgAddress; }
    public String getOrgPhone() { return orgPhone; }
    public void setOrgPhone(String orgPhone) { this.orgPhone = orgPhone; }
    public String getOrgEmail() { return orgEmail; }
    public void setOrgEmail(String orgEmail) { this.orgEmail = orgEmail; }
    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }

    @Override
    public String toString() {
        return "Organizer{" + "orgId=" + orgId + ", orgName='" + orgName + '\'' + '}';
    }
}
