package backend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class AccessToRole {
    @Id
    private String idAtoR;
    @DBRef
    private Role idRole;
    @DBRef
    private Access idAccess;

    //void constructor
    public AccessToRole(){}

    public AccessToRole(Role idRole, Access idAccess) {
        this.idRole = idRole;
        this.idAccess = idAccess;
    }
    //gets
    public String getIdAtoR() { return idAtoR; }
    public Role getIdRole() { return idRole; }
    public Access getIdAccess() { return idAccess; }

    //sets
    public void setIdRole(Role idRole) { this.idRole = idRole; }
    public void setIdAccess(Access idAccess) { this.idAccess = idAccess; }}
