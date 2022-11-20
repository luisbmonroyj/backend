package backend.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document()
public class Access {
    @Id
    private String idAccess;
    private String url;
    private String method;

    public Access (String url, String method) {
        this.url = url;
        this.method = method;
    }

    //gets
    public String getIdAccess() { return idAccess; }
    public String getUrl() { return url; }
    public String getMethod() { return method; }

    //sets
    public void setUrl(String url) { this.url = url; }
    public void setMethod(String method) { this.method = method; }
}
