package org.launchcode.codingevents.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

// Because persistent entities(class) such as Event and EventCategory are
// sharing primary keys aka id's the codes are duplicated. Since id related
// logics are duplicated across these two entities we will use inheritance
// to eliminate the duplication

// The fields in this class should be pushed down into the
// tables that extends it, in other words
// id here is private but I want to make
// the table that extends Event and EventCategory.
@MappedSuperclass
public abstract class AbstractEntity {

    // Telling that this is our primary key
    @Id
    // We want DB to generate the primary key
    @GeneratedValue
    private int id;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity entity = (AbstractEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
