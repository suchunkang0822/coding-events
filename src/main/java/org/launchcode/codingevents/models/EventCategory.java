package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity{
//    @Id
//    @GeneratedValue
//    private int id;
    @Size(min=3, message="Name must be at least 3 characters long")
    @NotBlank(message = "Event Category Name is required")
    private String name;

    // We need persistence annotation to determine
    // which Events should be in a EventCategory.
    // In other words how should hibernate know
    // which Events should be in a specific EventCategory.
    // The argument takes in the eventCategory field
    // of the Event class. It has to be a field in the
    // class we are string below. This relationship will be owned by
    // the foreign key or the reference of the Event class.
    @OneToMany(mappedBy = "eventCategory")
    // We have set up Many to One relationship with Event and EventCategory.
    // Now we will do inverse meaning One to many with the two entities.
    // This arraylist will store all the events associated with one EventCategory.
    private final List<Event> events = new ArrayList<>();


    public EventCategory(String name){
        this.name = name;
    }

    public EventCategory(){}

//    public int getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EventCategory that = (EventCategory) o;
//        return id == that.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
