package com.pradeep.user_service.services.impl;

import com.pradeep.user_service.entities.User;
import com.pradeep.user_service.exception.ResourceNotFoundException;
import com.pradeep.user_service.repository.UserRepository;
import com.pradeep.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository repository;

    @Autowired
    UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return repository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with given id " + userId + " is not found on the server."));
    }

    @Override
    public void deleteUser(String userId) {
        repository.deleteById(userId);
    }



    @Override
    public User updateUser(String userId, User updatedUser) {
        User user=getUser(userId);
        User updated=User.builder()
                .userId(user.getUserId())
                .name(updatedUser.getName())
                .email(updatedUser.getEmail())
                .about(updatedUser.getAbout()).build();
        if (user.getUserId()!=null){
            repository.save(updated);
        }
        return updated;
    }
}
