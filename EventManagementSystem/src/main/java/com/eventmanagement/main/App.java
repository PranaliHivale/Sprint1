package com.eventmanagement.main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import com.eventmanagement.daoimp.AttendeeDaoImpl;
import com.eventmanagement.daoimp.EventDaoImpl;
import com.eventmanagement.daoimp.OrganizerDaoImpl;
import com.eventmanagement.daoimp.VendorDaoImpl;
import com.eventmanagement.daoimp.VenueDaoImpl;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.entity.Vendor;
import com.eventmanagement.entity.Venue;
import com.eventmanagement.util.HibernateUtil;

public class App {
    private static final OrganizerDaoImpl organizerDao = new OrganizerDaoImpl();
    private static final VenueDaoImpl venueDao = new VenueDaoImpl();
    private static final AttendeeDaoImpl attendeeDao = new AttendeeDaoImpl();
    private static final VendorDaoImpl vendorDao = new VendorDaoImpl();
    private static final EventDaoImpl eventDao = new EventDaoImpl();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMainMenu();
            int ch = readInt(sc);
            switch (ch) {
                case 1: organizersMenu(sc); break;
                case 2: venuesMenu(sc); break;
                case 3: attendeesMenu(sc); break;
                case 4: vendorsMenu(sc); break;
                case 5: eventsMenu(sc); break;
                case 6:
                    System.out.println("Exiting...");
                    HibernateUtil.shutdown();
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== EVENT MANAGEMENT SYSTEM ===");
        System.out.println("1. Organizers");
        System.out.println("2. Venues");
        System.out.println("3. Attendees");
        System.out.println("4. Vendors");
        System.out.println("5. Events");
        System.out.println("6. Exit");
        System.out.print("Choice: ");
    }

    private static int readInt(Scanner sc) {
        try { return Integer.parseInt(sc.nextLine().trim()); } catch (Exception e) { return -1; }
    }

    // ----------------- Organizers -----------------
    private static void organizersMenu(Scanner sc) {
        System.out.println("\nOrganizers: 1.Add 2.List 3.Update 4.Delete 5.Back");
        int c = readInt(sc);
        switch (c) {
            case 1:
                Organizer o = new Organizer();
                System.out.print("Name: "); o.setOrgName(sc.nextLine());
                System.out.print("Address: "); o.setOrgAddress(sc.nextLine());
                System.out.print("Phone: "); o.setOrgPhone(sc.nextLine());
                System.out.print("Email: "); o.setOrgEmail(sc.nextLine());
                organizerDao.addOrganizer(o);
                System.out.println("Organizer added.");
                break;
            case 2:
                List<Organizer> orgs = organizerDao.getAllOrganizers();
                orgs.forEach(System.out::println);
                break;
            case 3:
                System.out.print("Organizer Id to update: ");
                int uid = readInt(sc);
                Organizer uo = organizerDao.getOrganizer(uid);
                if (uo == null) { System.out.println("Not found."); break; }
                System.out.print("New name ("+uo.getOrgName()+"): "); String nn = sc.nextLine(); if (!nn.isBlank()) uo.setOrgName(nn);
                System.out.print("New address ("+uo.getOrgAddress()+"): "); String na = sc.nextLine(); if (!na.isBlank()) uo.setOrgAddress(na);
                System.out.print("New phone ("+uo.getOrgPhone()+"): "); String np = sc.nextLine(); if (!np.isBlank()) uo.setOrgPhone(np);
                System.out.print("New email ("+uo.getOrgEmail()+"): "); String ne = sc.nextLine(); if (!ne.isBlank()) uo.setOrgEmail(ne);
                organizerDao.updateOrganizer(uo);
                System.out.println("Updated.");
                break;
            case 4:
                System.out.print("Organizer Id to delete: ");
                organizerDao.deleteOrganizer(readInt(sc));
                System.out.println("Deleted if existed.");
                break;
            case 5: return;
            default: System.out.println("Invalid");
        }
    }

    // ----------------- Venues -----------------
    private static void venuesMenu(Scanner sc) {
        System.out.println("\nVenues: 1.Add 2.List 3.Update 4.Delete 5.Back");
        int c = readInt(sc);
        switch (c) {
            case 1:
                Venue v = new Venue();
                System.out.print("Name: "); v.setVName(sc.nextLine());
                System.out.print("Address: "); v.setVAddress(sc.nextLine());
                System.out.print("Capacity: "); v.setVCapacity(Integer.parseInt(sc.nextLine()));
                System.out.print("Contact: "); v.setVContact(sc.nextLine());
                System.out.print("Owner: "); v.setVOwner(sc.nextLine());
                venueDao.addVenue(v);
                System.out.println("Venue added.");
                break;
            case 2:
                venueDao.getAllVenues().forEach(System.out::println);
                break;
            case 3:
                System.out.print("Venue Id to update: ");
                int vid = readInt(sc);
                Venue uv = venueDao.getVenue(vid);
                if (uv == null) { System.out.println("Not found."); break; }
                System.out.print("New name ("+uv.getVName()+"): "); String nv = sc.nextLine(); if (!nv.isBlank()) uv.setVName(nv);
                System.out.print("New address ("+uv.getVAddress()+"): "); String av = sc.nextLine(); if (!av.isBlank()) uv.setVAddress(av);
                System.out.print("New capacity ("+uv.getVCapacity()+"): "); String cv = sc.nextLine(); if (!cv.isBlank()) uv.setVCapacity(Integer.parseInt(cv));
                System.out.print("New contact ("+uv.getVContact()+"): "); String cont = sc.nextLine(); if (!cont.isBlank()) uv.setVContact(cont);
                System.out.print("New owner ("+uv.getVOwner()+"): "); String own = sc.nextLine(); if (!own.isBlank()) uv.setVOwner(own);
                venueDao.updateVenue(uv);
                System.out.println("Updated.");
                break;
            case 4:
                System.out.print("Venue Id to delete: ");
                venueDao.deleteVenue(readInt(sc));
                System.out.println("Deleted if existed.");
                break;
            case 5: return;
            default: System.out.println("Invalid");
        }
    }

    // ----------------- Attendees -----------------
    private static void attendeesMenu(Scanner sc) {
        System.out.println("\nAttendees: 1.Add 2.List 3.Update 4.Delete 5.Back");
        int c = readInt(sc);
        switch (c) {
            case 1:
                Attendee a = new Attendee();
                System.out.print("Name: "); a.setAttName(sc.nextLine());
                System.out.print("Email: "); a.setAttEmail(sc.nextLine());
                System.out.print("Phone: "); a.setAttPhone(sc.nextLine());
                System.out.print("Address: "); a.setAttAddress(sc.nextLine());
                attendeeDao.addAttendee(a);
                System.out.println("Attendee added.");
                break;
            case 2:
                attendeeDao.getAllAttendees().forEach(System.out::println);
                break;
            case 3:
                System.out.print("Attendee Id to update: ");
                int aid = readInt(sc);
                Attendee ua = attendeeDao.getAttendee(aid);
                if (ua == null) { System.out.println("Not found."); break; }
                System.out.print("New name ("+ua.getAttName()+"): "); String an = sc.nextLine(); if (!an.isBlank()) ua.setAttName(an);
                System.out.print("New email ("+ua.getAttEmail()+"): "); String ae = sc.nextLine(); if (!ae.isBlank()) ua.setAttEmail(ae);
                System.out.print("New phone ("+ua.getAttPhone()+"): "); String ap = sc.nextLine(); if (!ap.isBlank()) ua.setAttPhone(ap);
                System.out.print("New address ("+ua.getAttAddress()+"): "); String aa = sc.nextLine(); if (!aa.isBlank()) ua.setAttAddress(aa);
                attendeeDao.updateAttendee(ua);
                System.out.println("Updated.");
                break;
            case 4:
                System.out.print("Attendee Id to delete: ");
                attendeeDao.deleteAttendee(readInt(sc));
                System.out.println("Deleted if existed.");
                break;
            case 5: return;
            default: System.out.println("Invalid");
        }
    }

    // ----------------- Vendors -----------------
    private static void vendorsMenu(Scanner sc) {
        System.out.println("\nVendors: 1.Add 2.List 3.Update 4.Delete 5.Back");
        int c = readInt(sc);
        switch (c) {
            case 1:
                Vendor v = new Vendor();
                System.out.print("Name: "); v.setVendName(sc.nextLine());
                System.out.print("Service: "); v.setVendService(sc.nextLine());
                System.out.print("Contact: "); v.setVendContact(sc.nextLine());
                System.out.print("Email: "); v.setVendEmail(sc.nextLine());
                System.out.print("Address: "); v.setVendAddress(sc.nextLine());
                vendorDao.addVendor(v);
                System.out.println("Vendor added.");
                break;
            case 2:
                vendorDao.getAllVendors().forEach(System.out::println);
                break;
            case 3:
                System.out.print("Vendor Id to update: ");
                int vid = readInt(sc);
                Vendor uv = vendorDao.getVendor(vid);
                if (uv == null) { System.out.println("Not found."); break; }
                System.out.print("New name ("+uv.getVendName()+"): "); String vn = sc.nextLine(); if (!vn.isBlank()) uv.setVendName(vn);
                System.out.print("New service ("+uv.getVendService()+"): "); String vs = sc.nextLine(); if (!vs.isBlank()) uv.setVendService(vs);
                System.out.print("New contact ("+uv.getVendContact()+"): "); String vc = sc.nextLine(); if (!vc.isBlank()) uv.setVendContact(vc);
                System.out.print("New email ("+uv.getVendEmail()+"): "); String ve = sc.nextLine(); if (!ve.isBlank()) uv.setVendEmail(ve);
                System.out.print("New address ("+uv.getVendAddress()+"): "); String va = sc.nextLine(); if (!va.isBlank()) uv.setVendAddress(va);
                vendorDao.updateVendor(uv);
                System.out.println("Updated.");
                break;
            case 4:
                System.out.print("Vendor Id to delete: ");
                vendorDao.deleteVendor(readInt(sc));
                System.out.println("Deleted if existed.");
                break;
            case 5: return;
            default: System.out.println("Invalid");
        }
    }

    // ----------------- Events -----------------
    private static void eventsMenu(Scanner sc) {
        System.out.println("\nEvents: 1.Add 2.List 3.Update 4.Delete 5.AddAttendee 6.RemoveAttendee 7.AddVendor 8.RemoveVendor 9.ViewDetails 10.Back");
        int c = readInt(sc);
        switch (c) {
            case 1:
                Event e = new Event();
                System.out.print("Name: "); e.setEventName(sc.nextLine());
                System.out.print("Type: "); e.setEventType(sc.nextLine());
                System.out.print("Date (YYYY-MM-DD): "); e.setEventDate(LocalDate.parse(sc.nextLine()));
                System.out.print("Time (HH:MM): "); e.setEventTime(LocalTime.parse(sc.nextLine()+":00").withSecond(0));
                System.out.print("Organizer Id: "); int oid = readInt(sc);
                Organizer org = organizerDao.getOrganizer(oid);
                e.setOrganizer(org);
                System.out.print("Venue Id: "); int vid = readInt(sc);
                Venue venue = venueDao.getVenue(vid);
                e.setVenue(venue);
                eventDao.addEvent(e);
                System.out.println("Event added.");
                break;
            case 2:
                eventDao.getAllEvents().forEach(System.out::println);
                break;
            case 3:
                System.out.print("Event Id to update: ");
                int eid = readInt(sc);
                Event ue = eventDao.getEvent(eid);
                if (ue == null) { System.out.println("Not found."); break; }
                System.out.print("New name ("+ue.getEventName()+"): "); String en = sc.nextLine(); if (!en.isBlank()) ue.setEventName(en);
                System.out.print("New type ("+ue.getEventType()+"): "); String et = sc.nextLine(); if (!et.isBlank()) ue.setEventType(et);
                System.out.print("New date ("+(ue.getEventDate()!=null?ue.getEventDate():"")+"): "); String ed = sc.nextLine(); if (!ed.isBlank()) ue.setEventDate(LocalDate.parse(ed));
                System.out.print("New time ("+(ue.getEventTime()!=null?ue.getEventTime():"")+"): "); String etime = sc.nextLine(); if (!etime.isBlank()) ue.setEventTime(LocalTime.parse(etime+":00"));
                System.out.print("New Organizer Id (blank to keep): "); String oidS = sc.nextLine(); if (!oidS.isBlank()) ue.setOrganizer(organizerDao.getOrganizer(Integer.parseInt(oidS)));
                System.out.print("New Venue Id (blank to keep): "); String vidS = sc.nextLine(); if (!vidS.isBlank()) ue.setVenue(venueDao.getVenue(Integer.parseInt(vidS)));
                eventDao.updateEvent(ue);
                System.out.println("Updated.");
                break;
            case 4:
                System.out.print("Event Id to delete: ");
                eventDao.deleteEvent(readInt(sc));
                System.out.println("Deleted if existed.");
                break;
            case 5:
                System.out.print("Event Id: "); int eId1 = readInt(sc);
                System.out.print("Attendee Id: "); int aId1 = readInt(sc);
                Attendee att = attendeeDao.getAttendee(aId1);
                if (att == null) System.out.println("Attendee not found.");
                else eventDao.addAttendeeToEvent(eId1, att);
                System.out.println("Done.");
                break;
            case 6:
                System.out.print("Event Id: "); int eId2 = readInt(sc);
                System.out.print("Attendee Id to remove: "); int aId2 = readInt(sc);
                eventDao.removeAttendeeFromEvent(eId2, aId2);
                System.out.println("Done.");
                break;
            case 7:
                System.out.print("Event Id: "); int eId3 = readInt(sc);
                System.out.print("Vendor Id: "); int vId3 = readInt(sc);
                Vendor vend = vendorDao.getVendor(vId3);
                if (vend == null) System.out.println("Vendor not found.");
                else eventDao.addVendorToEvent(eId3, vend);
                System.out.println("Done.");
                break;
            case 8:
                System.out.print("Event Id: "); int eId4 = readInt(sc);
                System.out.print("Vendor Id to remove: "); int vId4 = readInt(sc);
                eventDao.removeVendorFromEvent(eId4, vId4);
                System.out.println("Done.");
                break;
            case 9:
                System.out.print("Event Id to view: ");
                int viewId = readInt(sc);
                Event view = eventDao.getEvent(viewId);
                if (view == null) { System.out.println("Not found."); break; }
                System.out.println(view);
                System.out.println(" Organizer: " + (view.getOrganizer()!=null?view.getOrganizer().getOrgName():"-"));
                System.out.println(" Venue: " + (view.getVenue()!=null?view.getVenue().getVName():"-"));
                System.out.println(" Attendees:");
                view.getAttendees().forEach(ae -> System.out.println("  " + ae));
                System.out.println(" Vendors:");
                view.getVendors().forEach(vd -> System.out.println("  " + vd));
                break;
            case 10: return;
            default: System.out.println("Invalid");
        }
    }
}
