package backend.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document()
public class User {
        @Id
        private String id;
        private String lastname;
        private String name;
        private String email;
        private String address;
        private String phone;
        private String username;
        private String password;
        @DBRef
        private Role idRole;

        public User (String id,String lastname,String name,
                     String email,String address,String phone,
                     String username,String password){
                this.id = id;
                this.lastname=lastname;
                this.name=name;
                this.email=email;
                this.address=address;
                this.phone=phone;
                this.username = username;
                this.password=password;
        }

        //gets
        public String getId(){ return this.id; }
        public String getLastname(){ return this.lastname; }
        public String getName(){ return this.name; }
        public String getEmail(){ return this.email; }
        public String getAddress(){ return this.address; }
        public String getPhone(){ return this.phone; }
        public String getUsername(){ return this.username; }
        public String getPassword(){ return this.password; }
        public Role getIdRole (){ return this.idRole;}

        //sets
        public void setLastname(String lastname){ this.lastname = lastname;}
        public void setName(String name){ this.name = name;}
        public void setEmail(String email){ this.email = email;}
        public void setAddress(String address){ this.address = address;}
        public void setPhone(String phone){ this.phone = phone;}
        public void setUsername(String username){ this.username = username;}
        public void setPassword(String password){ this.password = password;}
        public void setIdRole(Role idRole) { this.idRole = idRole;}

}
