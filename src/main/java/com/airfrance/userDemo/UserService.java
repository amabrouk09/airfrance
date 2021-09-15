package com.airfrance.userDemo;

import java.util.Optional;

public interface UserService {
     User createUser(User user) throws UserException ;
     Optional<User> findUserById(int id) throws UserException;
}
