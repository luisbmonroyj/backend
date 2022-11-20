package backend.repositories;
import backend.models.Access;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AccessRepo extends MongoRepository<Access,String> {
    @Query("{'url':?0,'method':?1}")
    Access getAccess(String url, String method);
}
