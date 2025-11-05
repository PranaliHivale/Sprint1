package com.eventmanagement.dao;

import com.eventmanagement.entity.Vendor;
import java.util.List;

public interface VendorDao {
    void addVendor(Vendor v);
    Vendor getVendor(int id);
    List<Vendor> getAllVendors();
    void updateVendor(Vendor v);
    void deleteVendor(int id);
}
