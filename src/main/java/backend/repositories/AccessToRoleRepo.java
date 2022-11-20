package backend.repositories;
import backend.models.AccessToRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccessToRoleRepo extends MongoRepository <AccessToRole,String> {
    @Query("{'idAccess': ?0}")
    List<AccessToRole> getRolesWithAccess(String idAccess);
    @Query("{'idRole': ?0}")
    List<AccessToRole> getRoleAccesses(String idRole);
    @Query("{'idRole': ?0,'idAccess': ?1}")
    AccessToRole getRoleAccess(String idRole, String idAccess);
}
