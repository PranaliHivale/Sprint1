package com.eventmanagement.dao;

import com.eventmanagement.entity.Venue;
import java.util.List;

public interface VenueDao {
    void addVenue(Venue v);
    Venue getVenue(int id);
    List<Venue> getAllVenues();
    void updateVenue(Venue v);
    void deleteVenue(int id);
}
