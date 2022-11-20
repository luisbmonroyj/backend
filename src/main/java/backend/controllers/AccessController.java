package backend.controllers;
import backend.models.Access;
import backend.repositories.AccessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/access")

public class AccessController{
    @Autowired
    private AccessRepo myAccessRepo;
    @GetMapping("")
    public List<Access> index(){ return this.myAccessRepo.findAll(); }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Access create(@RequestBody Access newAccess){
        //Check if there is not an access with the same properties
        Access test = myAccessRepo.getAccess(newAccess.getUrl(),newAccess.getMethod());
        if (test!= null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "There is already an Access with id " + test.getIdAccess());
        else
            return this.myAccessRepo.save(newAccess);
    }/*
    @GetMapping()
    public Access show(@RequestParam String id){
        Access currentAccess=this.myAccessRepo.findById(id).orElse(null);
        if (currentAccess==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Access does not exist");
        }
        return currentAccess;
    }*/
    @PutMapping()
    public Access update(@RequestBody Access updateAccess){
        Access currentAccess=this.myAccessRepo.findById(updateAccess.getIdAccess()).orElse(null);
        if (currentAccess!=null){
            //check if there isn't an access with the same url and method
            currentAccess.setUrl(updateAccess.getUrl());
            currentAccess.setMethod(updateAccess.getMethod());
            Access test = myAccessRepo.getAccess(updateAccess.getUrl(), updateAccess.getMethod());
            if (test!=null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is already an Access with the same url and method, its id is "+test.getIdAccess());
            else
                return this.myAccessRepo.save(currentAccess);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no such Access you are trying to update");
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    public void delete(@RequestParam String id){
        Access currentAccess=this.myAccessRepo.findById(id).orElse(null);
        if (currentAccess!=null)
            this.myAccessRepo.delete(currentAccess);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Access does not exist");
    }
}
