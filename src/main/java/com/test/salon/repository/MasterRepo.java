package com.test.salon.repository;

import com.test.salon.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MasterRepo extends JpaRepository<Master, Long> {

    List<Master> findByRang(Integer rang);
    List<Master> findByName(String name);
}
