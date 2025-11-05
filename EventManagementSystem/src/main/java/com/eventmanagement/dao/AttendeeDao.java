package com.eventmanagement.dao;

import com.eventmanagement.entity.Attendee;
import java.util.List;

public interface AttendeeDao {
    void addAttendee(Attendee a);
    Attendee getAttendee(int id);
    List<Attendee> getAllAttendees();
    void updateAttendee(Attendee a);
    void deleteAttendee(int id);
}
