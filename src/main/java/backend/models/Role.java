package backend.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document()
public class Role {
    @Id
    private String idRole;
    private String name;
    private String description;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //gets
    public String getIdRole() { return idRole; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    //sets
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
}
