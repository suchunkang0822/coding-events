package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Flags SpringBoot that this is the class this will be
// repository class that's going to store obj in the DB.
// Also Spring creates an instance to be available for
// other parts of the application as well.
// In other word, Spring will create this instance and
// inject them wherever it's needed
@Repository
// Notice that there is no concrete class that implements
// EventRepository. How do we end up eventRepository obj in
// the Event controller?
// A:
// CrudRepository is a special interface
// it allows our code to store and retrieve
// obj instance from our DB. Since CrudRepository
// is so boilerplate, Spring is actually able to create
// these classes for us. When the application starts up
// Spring will see the EventRepository interface and it will
// in memory on the fly, create a class that implements
// EventRepository in the application's memory and then
// it will make sure that it can be @Autowired
public interface EventRepository extends CrudRepository<Event,Integer> {
}
