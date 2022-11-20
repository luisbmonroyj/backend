package backend.controllers;
import backend.models.Role;
import backend.models.User;
import backend.repositories.RoleRepo;
import backend.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/role")

public class RoleController {
    @Autowired
    private UserRepo myUserRepo;
    @Autowired
    private RoleRepo myRoleRepo;

    @GetMapping("")
    public List<Role> index(){ return this.myRoleRepo.findAll(); }

    @PostMapping()
    public Role create(@RequestBody Role newRole){
        //Check if the role name does not exist
        Role test = myRoleRepo.getRoleByName(newRole.getName());
        if (test!=null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role already exists with id "+test.getIdRole());
        else
            return this.myRoleRepo.save(newRole);
    }

    @PutMapping()
    public Role update(@RequestBody Role updateRole){
        Role currentRole=this.myRoleRepo.findById(updateRole.getIdRole()).orElse(null);
        //first, check if the role exists
        if (currentRole!=null){
            //check if the new role name is not used already
            Role test = myRoleRepo.getRoleByName(updateRole.getName());
            if (test!=null)
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Role already exists with id "+test.getIdRole());
            else {
                currentRole.setName(updateRole.getName());
                currentRole.setDescription(updateRole.getDescription());
                return this.myRoleRepo.save(currentRole);
            }
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The role you are trying to update does not exist");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    public void delete(@RequestParam String id){
        Role currentRole=this.myRoleRepo.findById(id).orElse(null);
        if (currentRole!=null){
            //avoid to delete the role named "undefined"
            if (currentRole.getName().equals("undefined"))
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Role undefined cannot be deleted");
            else {
                //get an instance of Role undefined
                Role undefinedRole = this.myRoleRepo.getRoleByName("undefined");
                //make a list of users with the role to be deleted
                List<User> usersToChange= this.myUserRepo.getUsersWithRole(currentRole.getIdRole());
                if (usersToChange.size()>0)
                    for (User userToUndefined : usersToChange) {
                        //change role to -undefined- to this users
                        userToUndefined.setIdRole(undefinedRole);
                        this.myUserRepo.save(userToUndefined);
                    }
                //now I can delete the role
                this.myRoleRepo.delete(currentRole);
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Role you are trying to delete does not exist");
        }
    }

}
