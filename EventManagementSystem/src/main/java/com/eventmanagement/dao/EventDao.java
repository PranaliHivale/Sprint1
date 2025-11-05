package com.eventmanagement.dao;

import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Vendor;

import java.util.List;

public interface EventDao {
    void addEvent(Event e);
    Event getEvent(int id);
    List<Event> getAllEvents();
    void updateEvent(Event e);
    void deleteEvent(int id);

    void addAttendeeToEvent(int eventId, Attendee a);
    void removeAttendeeFromEvent(int eventId, int attendeeId);

    void addVendorToEvent(int eventId, Vendor v);
    void removeVendorFromEvent(int eventId, int vendorId);
}
