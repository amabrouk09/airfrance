package com.airfrance.userDemo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(User user) throws  UserException{
        if(!"france".equalsIgnoreCase(user.getPays()) && user.getAge()<18)
            throw new UserException("user under 18yo or not living in france");
        return userRepo.save(user);
    }

    @Override
    public Optional<User> findUserById(int id) throws UserException {
        if(!userRepo.findById(id).isPresent())
            throw new UserException("User not found");
        return userRepo.findById(id);
    }
}
