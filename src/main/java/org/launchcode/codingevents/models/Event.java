package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Objects;

// Flagging Spring that this is persistent obj
// that can be stored outside of this application in a DB.
// This is the bare minimum we need to make this class persistent
@Entity
public class Event {
    // Telling that this is our primary key
    @Id
    // We want DB to generate the primary key
    @GeneratedValue
    private int id;

    // Now we don't need to manually generate the id
    // since DB will take care of that for use from
    // the annotation above
//    private static int nextId=1;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;

    @Size(max = 500, message = "Description is too long.")
    private String description;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    @NotBlank(message = "Location is required")
    @NotNull(message = "Location can't be Null")
    private String location;

    @AssertTrue(message = "Registration is required for attendee")
    private boolean isRegistered;

    @Positive(message = "Attendees must be bigger than zero")
    private int attendees;

    private EventType type;

    // We ALWAYS NEED EMPTY CONSTRUCTOR FOR THE
    // PERSISTENT CLASS!!!!! JPA uses it to
    // instantiate an obj
    public Event(){
//        this.id = nextId;
//        nextId++;
    }

    public Event(String name, String description, String contactEmail,
                 String location, boolean isRegistered, int attendees, EventType type) {
        // Not needed
        //this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.location = location;
        this.isRegistered = isRegistered;
        this.attendees = attendees;
        this.type = type;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getId() {
        return id/2;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
