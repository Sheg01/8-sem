package com.test.salon.repository;

import com.test.salon.model.Contact;
import com.test.salon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact, Long> {
    List<Contact> findByClient(User client);
}
