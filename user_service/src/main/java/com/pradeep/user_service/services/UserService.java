package com.pradeep.user_service.services;

import com.pradeep.user_service.entities.User;

import java.util.List;

public interface UserService {

    //user operations
    //create
    User createUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user
    User getUser(String userId);

    //delete user
    void deleteUser(String userId);

    //update user

    User updateUser(String userId, User updatedUser);
}
