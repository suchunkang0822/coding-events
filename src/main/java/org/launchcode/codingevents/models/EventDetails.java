package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

@Entity
public class EventDetails extends AbstractEntity{

    // Event and EventDetails has one to one unidirectional
    // relationship meaning Event knows which EventDetail
    // it is associsated with. However, it is not the other way around.
    // this won't be used here but we'll establish the inverse relationship
    // for the sake of completeness. This WON'T create a new foreign key column
    // but will use its primary id that is already associated with the Event to
    // establish inverse One to One with the Event.

    // the annotation says in order to populate the event obj
    // look for the Event obj that has the eventDetails
    @OneToOne(mappedBy = "eventDetails")
    private Event event;

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

    public EventDetails(String description, String contactEmail,
                        String location, boolean isRegistered, int attendees) {
        this.description = description;
        this.contactEmail = contactEmail;
        this.location = location;
        this.isRegistered = isRegistered;
        this.attendees = attendees;
    }

    public EventDetails(){}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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

    public void setIsRegistered(boolean registered) {
        isRegistered = registered;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }
}
