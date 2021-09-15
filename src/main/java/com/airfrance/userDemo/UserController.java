package com.airfrance.userDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    @Qualifier(value = "userService")
    UserService userService;

    private final Logger logger=  LoggerFactory.getLogger(this.getClass());

    /**
     * Methode to fecth user by id.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") int id) throws UserException{
        logger.debug("Getting users with user id = {}",id);
        return new ResponseEntity(userService.findUserById(id),HttpStatus.OK) ;
    }

    /**
     * Methode to save new user
     * @param user
     */
    @PostMapping("/save")
    public ResponseEntity saveUser(@RequestBody User user) throws UserException{
        return new ResponseEntity(userService.createUser(user), HttpStatus.CREATED);
    }

}
