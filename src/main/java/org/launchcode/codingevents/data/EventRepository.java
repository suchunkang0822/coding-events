package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Flags SpringBoot that this is the class this will be
// repository class that's going to store obj in the DB
@Repository
public interface EventRepository extends CrudRepository<Event,Integer> {
}
