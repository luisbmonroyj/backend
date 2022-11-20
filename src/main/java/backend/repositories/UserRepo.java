package backend.repositories;

import backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepo extends MongoRepository<User,String> {
    @Query("{'username': ?0}")
    User getUsername(String username);
    @Query("{'id': ?0}")
    User getUserByID(String id);
    @Query("{'idRole': ?0}")
    List<User> getUsersWithRole(String idRole);
}
