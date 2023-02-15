package com.test.salon.repository;

import com.test.salon.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceTypeRepo extends JpaRepository<ServiceType, Long> {

    List<ServiceType> findByRang(Integer rang);
    List<ServiceType> findByServiceGroup(String serviceGroup);
    List<ServiceType> findByName(String name);

    @Query(nativeQuery = true, value="select name from service_type group by name")
    List<String> groupServiceTypes();
}
