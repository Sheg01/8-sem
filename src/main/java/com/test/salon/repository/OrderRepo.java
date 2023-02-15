package com.test.salon.repository;

import com.test.salon.model.Order;
import com.test.salon.model.ServiceType;
import com.test.salon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderRepo extends JpaRepository<Order, Long>{
    List<Order> findByClient(User user);
    int countAllByServiceTypeName(ServiceType serviceTypeName);

    @Query(nativeQuery = true, value ="select service_type_id, COUNT(service_type_id) as w from ordr group by service_type_id")
    Map<String, Integer> countedServicesGroups();

    @Query(nativeQuery = true, value="select count(service_type_id) from ordr group by service_type_id order by service_type_id")
    List<Integer> countServiceTypes();
}
