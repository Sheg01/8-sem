package com.test.salon.service;

import com.test.salon.repository.ContactRepo;
import com.test.salon.repository.UserRepo;
import com.test.salon.model.Contact;
import com.test.salon.model.Role;
import com.test.salon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class RegistrationService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContactRepo contactRepo;

    public User findUserByUsername(User user){
        return userRepo.findByUsername(user.getUsername());
    }

    public void saveUser(User user){
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }

    public void saveContact(Contact contact){
        contactRepo.save(contact);
    }
}
