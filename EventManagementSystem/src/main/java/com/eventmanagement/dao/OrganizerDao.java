package com.eventmanagement.dao;

import com.eventmanagement.entity.Organizer;
import java.util.List;

public interface OrganizerDao {
    void addOrganizer(Organizer o);
    Organizer getOrganizer(int id);
    List<Organizer> getAllOrganizers();
    void updateOrganizer(Organizer o);
    void deleteOrganizer(int id);
}
