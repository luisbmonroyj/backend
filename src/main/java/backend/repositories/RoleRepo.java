package backend.repositories;
import backend.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RoleRepo extends MongoRepository <Role,String> {
    @Query("{'name':?0}")
    Role getRoleByName(String name);
    @Query("{'idRole':?0}")
    Role getRoleById(String idRole);
}
