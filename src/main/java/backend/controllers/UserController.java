package backend.controllers;
import backend.models.Role;
import backend.models.User;
import backend.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import backend.repositories.UserRepo;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@CrossOrigin
@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserRepo myUserRepo;
    @Autowired
    private RoleRepo myRoleRepo;

    @GetMapping("")
    public List<User> index(){ return this.myUserRepo.findAll(); }

    @PostMapping()
    public User create(@RequestBody User newUser){
        //Check if the username does not exist
        User test = myUserRepo.getUsername(newUser.getUsername());
        if (test!=null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");// with id "+test.get_id());
        else {
            Role undefined = myRoleRepo.getRoleByName("undefined");
            newUser.setIdRole(undefined);
            newUser.setPassword(encryptSHA256(newUser.getPassword()));
            return this.myUserRepo.save(newUser);
        }
    }

    @PatchMapping()
    public User update(@RequestBody User updateUser){
        //first, check if the user exists
        User currentUser=this.myUserRepo.findById(updateUser.getId()).orElse(null);
        if (currentUser!=null){
            //check if the new username is not used already with a different id
            User test = myUserRepo.getUsername(updateUser.getUsername());
            if (test!=null&!(updateUser.getUsername().equals(currentUser.getUsername()))) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");// with id "+test.get_id());
            }
            else {
                currentUser.setLastname(updateUser.getLastname());
                currentUser.setName(updateUser.getName());
                currentUser.setEmail(updateUser.getEmail());
                currentUser.setAddress(updateUser.getAddress());
                currentUser.setPhone(updateUser.getPhone());
                currentUser.setUsername(updateUser.getUsername());
                currentUser.setPassword(encryptSHA256(updateUser.getPassword()));
                return this.myUserRepo.save(currentUser);
            }
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user you are trying to update does not exist");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    public void delete(@RequestParam String id){
        User currentUser=this.myUserRepo.findById(id).orElse(null);
        if (currentUser!=null)
            this.myUserRepo.delete(currentUser);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user you are trying to delete does not exist");
    }
    @PostMapping("/validate")
    public User validate(@RequestBody User infoUser,final HttpServletResponse response) throws IOException {
        User currentUser=this.myUserRepo.getUsername(infoUser.getUsername());
        if (currentUser!=null && currentUser.getPassword().equals(encryptSHA256(infoUser.getPassword()))) {
            currentUser.setPassword("");
            return currentUser;
        }
        else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }
    @PutMapping()
    public User setRoleToUser(@RequestParam String id, @RequestParam String idRole){
        User currentUser = this.myUserRepo.findById(id).orElseThrow(RuntimeException::new);
        //check if the user exists
        if (currentUser==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        else{
            Role currentRole = this.myRoleRepo.findById(idRole).orElseThrow(RuntimeException::new);
            if (currentRole==null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role does not exist");
                //if role exists, assign it to the user
            else
                currentUser.setIdRole(currentRole);
        }
        return this.myUserRepo.save(currentUser);
    }

    public String encryptSHA256(String password) {
        MessageDigest msgDgst;
        try { msgDgst = MessageDigest.getInstance("SHA-256"); }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] msgBytes = msgDgst.digest(password.getBytes());
        StringBuffer buffer = new StringBuffer();
        for (byte b : msgBytes)
            buffer.append(String.format("%02x", b));
        return buffer.toString();
    }

}
