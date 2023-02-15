package com.test.salon.service.order;

import com.test.salon.repository.MasterRepo;
import com.test.salon.repository.OrderRepo;
import com.test.salon.repository.ServiceTypeRepo;
import com.test.salon.model.Master;
import com.test.salon.model.Order;
import com.test.salon.model.ServiceType;
import com.test.salon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private ServiceTypeRepo serviceTypeRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private MasterRepo masterRepo;

    public List<Order> findOrdersByClient(User user){
        return orderRepo.findByClient(user);
    }

    public List<Order> findAllOrders(){
        return orderRepo.findAll();
    }

    public HashSet<String> findServiceGroups(){
        List<ServiceType> serviceTypes = serviceTypeRepo.findAll();

        HashSet<String> serviceGroups = new HashSet<>();
        for (int i = 0; i < serviceTypes.size(); i++) {
            serviceGroups.add(serviceTypes.get(i).getServiceGroup());
        }
        return serviceGroups;
    }

    public List<ServiceType> findByServiceGroups(String serviceGroup){
        return serviceTypeRepo.findByServiceGroup(serviceGroup);
    }

    public List<ServiceType> findTypesByName(String serviceType){
        return serviceTypeRepo.findByName(serviceType);
    }

    public List<Master> findMastersByRang(Integer currentRang){
        return masterRepo.findByRang(currentRang);
    }

    public List<Master> findMasterByName(String master){
        return masterRepo.findByName(master);
    }

    public void saveOrder(Order order){
        orderRepo.save(order);
    }
}
