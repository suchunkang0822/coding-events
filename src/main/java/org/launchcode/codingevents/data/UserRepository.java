package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {

    // This isn't a regular method. It's a derived query method
    // enabled by JPA to retrieve data from DB.
    // This method stars by keyword 'findBy' followed by
    // the entity User's attribute 'name' to specify the criteria
    // which will return the User entity with that name.

    // Similarly,
    // Optional<User> findByEmail(String email);
    // will get you the user with corresponding email, if it exists.


    User findByUsername(String username);
}
