package com.charlesxvr.springsecurity6.services.imp;

import com.charlesxvr.springsecurity6.models.User;
import com.charlesxvr.springsecurity6.repository.UserRepository;
import com.charlesxvr.springsecurity6.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public User newUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User currentUser = this.userRepository.findById(id).orElse(null);
        if(currentUser != null) {
            currentUser.setName(user.getName());
            currentUser.setUsername(user.getUsername());
            if(user.getPassword() == null) {
                currentUser.setPassword(currentUser.getPassword());
            }
            return this.userRepository.save(currentUser);
        }
        return null;
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }
}
