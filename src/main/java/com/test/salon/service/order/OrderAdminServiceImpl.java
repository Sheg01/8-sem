package com.test.salon.service.order;

import com.test.salon.repository.MasterRepo;
import com.test.salon.repository.OrderRepo;
import com.test.salon.model.Master;
import com.test.salon.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAdminServiceImpl implements OrderAdminService{
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private MasterRepo masterRepo;

    public List<Order> findAllOrders(){
        return orderRepo.findAll();
    }

    public List<Master> orderAdminEditForm(Order order){
        return masterRepo.findByRang(order.getServiceTypeName().getRang());
    }

    public void deleteOrder(Order order){
        orderRepo.deleteById(order.getId());
    }

    public void orderAdminSubmit(Order order, String master){
        String status = "submitted";

        List<Master> masters = masterRepo.findByName(master);
        Master currentMaster = masters.get(0);

        order.setMaster(currentMaster);
        order.setOrderStatus(status);
        orderRepo.save(order);
    }
}
