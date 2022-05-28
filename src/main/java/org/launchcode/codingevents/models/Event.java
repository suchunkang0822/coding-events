package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Flagging Spring that this is persistent obj
// that can be stored outside of this application in a DB.
// This is the bare minimum we need to make this class persistent
@Entity
public class Event extends AbstractEntity{
    // You
//    // Telling that this is our primary key
//    @Id
//    // We want DB to generate the primary key
//    @GeneratedValue
//    private int id;

    // Now we don't need to manually generate the id
    // since DB will take care of that for use from
    // the annotation above
//    private static int nextId=1;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;

    // Encapsulating Event details, hence below fields
    // are now in EventDetail entity. These will be
    // replaced by eventDetail
//    @Size(max = 500, message = "Description is too long.")
//    private String description;
//
//    @NotBlank(message = "Email is required")
//    @Email(message = "Invalid email. Try again.")
//    private String contactEmail;
//
//    @NotBlank(message = "Location is required")
//    @NotNull(message = "Location can't be Null")
//    private String location;
//
//    @AssertTrue(message = "Registration is required for attendee")
//    private boolean isRegistered;
//
//    @Positive(message = "Attendees must be bigger than zero")
//    private int attendees;


    // we'll use EventCategory entity to
    // create many-to-one relational DB
    // Many Events to one EventCategory.
    // Hence we'll no longer need enum along
    // with the below
//    private EventType type;

    // This cascade asks when Event is saved, also
    // save EventDetails as well. It's same as
    // making EventDetailsRepository and doing .save()
    // in the createEvent controller. But of course, this is quicker.
    // CascadeType.All, means that whatever operations that is done
    // cascade it down to eventDetail. so if you delete an Event,
    // it will be applied to eventDetail as well.
    @OneToOne(cascade = CascadeType.ALL )
    // The reason why we add @Valid is that when we wnat to
    // validate our new Event obj in the controller when
    // there are model binding and form submission going on,
    //  when we add @Valid to that event(in the EventController) it WON'T validate
    // obj contained within the Event, it'll only validate
    // top level fields such as 'name' above.
    // By having this @Valid here, it will check the fields
    // of the eventDetails inside such as the description,email,etc .
    @Valid
    // Checks if there was an en EventDetails obj
    // but it would not go into check the fields inside.
    // so you need the @valid above
    @NotNull
    // We are not using constructor and will rely on
    // setter and model binding to populate eventDetails.
    private EventDetails eventDetails;

    // below annotation maps many Events to one EventCategory
    // relational DB
    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;


    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    // We ALWAYS NEED EMPTY CONSTRUCTOR FOR THE
    // PERSISTENT CLASS!!!!! JPA uses it to
    // instantiate an obj
    public Event(){
//        this.id = nextId;
//        nextId++;
    }

    public Event(String name,
//                 String description,
//                 String contactEmail,
//                 String location,
//                 boolean isRegistered,
//                 int attendees,
//                 EventType type,
                    EventCategory eventCategory) {
        // Not needed
        //this();
        this.name = name;
//        this.description = description;
//        this.contactEmail = contactEmail;
//        this.location = location;
//        this.isRegistered = isRegistered;
//        this.attendees = attendees;
        this.eventCategory = eventCategory;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getContactEmail() {
//        return contactEmail;
//    }
//
//    public void setContactEmail(String contactEmail) {
//        this.contactEmail = contactEmail;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public boolean getIsRegistered() {
//        return isRegistered;
//    }
//
//    public void setIsRegistered(boolean isRegistered) {
//        this.isRegistered = isRegistered;
//    }
//
//    public int getAttendees() {
//        return attendees;
//    }
//
//    public void setAttendees(int attendees) {
//        this.attendees = attendees;
//    }


    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    //    public EventType getType() {
//        return type;
//    }
//    public void setType(EventType type) {
//        this.type = type;
//    }



//    public int getId() {
//        return id;
//    }


    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Event event = (Event) o;
//        return id == event.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
