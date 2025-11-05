# EventManagementSystem (Console-based, Hibernate + Maven)

## Overview
This is a console-based Java (Hibernate) project that models an Event Management System with entities:
- Organizer
- Venue
- Attendee
- Vendor
- Event

Relationships:
- Organizer (1) -> Event (many)
- Event -> Venue (many-to-one)
- Event <-> Attendee (many-to-many)
- Event <-> Vendor (many-to-many)

## Requirements
- Java 24
- Maven
- MySQL running locally with a database named `eventdb`
- DB user: `root` / password: `root@123`

You do **not** need to create the DB tables manually — Hibernate's `hbm2ddl.auto=update` will create/update tables.

## How to run
1. Import the project into Eclipse as a Maven project.
2. Ensure MySQL is running and `eventdb` database exists (create the database if not present).
   - You can create DB using: `CREATE DATABASE eventdb;`
3. Run `com.eventmanagement.main.App` as a Java application.
4. The application will insert sample data and print some queries.

## Notes
- The project uses Hibernate 6 and Jakarta Persistence API.
- A runnable shaded jar is configured (via maven-shade-plugin); you can `mvn package` to build.
