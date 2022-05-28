package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity{

    @Size(min = 3,max = 25,message = "Has to be in between 1 to 25 characters")
    @NotNull(message = "Can't be empty")
    private String name;

    // In a many to many bidirectional relationship,
    // there exists a relationship that is Owner(one that doesn't have mappedBy)
    // and inverse end(one that does have mappedBy). The owner side is where hibernate
    // looks at to know which association exists. Due to the limitation of
    // foreign key associating a single record, when it comes to many-to-many
    // relationship DB will make a joint table in the form of owner_inverse-end.
    // If you add Tag object (the inverse end) in the Event obj, the hibernate
    // will create new row in the joint table however, if you add event in the
    // tag the DB will not be modified.
    @ManyToMany(mappedBy = "tags")
    private List<Event> events = new ArrayList<>();

    public Tag(String name){
        this.name = name;
    }
    public Tag(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName(){
        return "#"+name+" ";
    }
}
