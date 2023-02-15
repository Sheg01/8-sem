package com.test.salon.service;

import com.test.salon.repository.OrderRepo;
import com.test.salon.repository.ServiceTypeRepo;
import com.test.salon.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.function.Predicate.not;

@Service
public class StatisticsService {

    static final Integer ZERO_EARNINGS = 0;
    static final Integer MASTER_LOW_RANG = 3;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ServiceTypeRepo serviceTypeRepo;

    public Map<String, Integer> countedServiceTypes(){

        List<String> groupServiceTypes = serviceTypeRepo.groupServiceTypes();
        List<Integer> countServiceTypes = orderRepo.countServiceTypes();
        Map<String, Integer> servicesAndCounts = new HashMap<String, Integer>();

        for (int i = 0; i < groupServiceTypes.size(); i++) {
            servicesAndCounts.put(groupServiceTypes.get(i), countServiceTypes.get(i));
        }
        //List<Order> orders=orderRepo.findAll();

        //Map<String, Integer> servicesAndCounts = new HashMap<String, Integer>();
        //for (int i = 0; i < orders.size(); i++) {
        //    servicesAndCounts.put(orders.get(i).getServiceTypeName().getName(),
        //            orderRepo.countAllByServiceTypeName(orders.get(i).getServiceTypeName()));
        //}
        return servicesAndCounts;
    }

    public List<Order> getOrders(){
       return orderRepo.findAll();
    }

    public Integer countHighRangServiceTypes(){
        List<Order> orders = orderRepo.findAll();

        /* STREAM API
        List<Order> filteredOrders = orders.stream().filter(not(order -> order.getServiceTypeName().getRang()
                .equals(MASTER_LOW_RANG))).collect(Collectors.toList());

        Integer sum = filteredOrders.stream().reduce(0,
                (x,y) -> {return x + y.getServiceTypeName().getPrice();},
                Integer::sum);
         */
        /* IMPERATIVE
        Integer highRangServiceTypesEarning=ZERO_EARNINGS;
        for (Order order : filteredOrders) {
            if (!Objects.equals(order.getServiceTypeName().getRang(), MASTER_LOW_RANG)) {
                highRangServiceTypesEarning += order.getServiceTypeName().getPrice();
            }
        }
         */

        return orders.stream().filter(not(order -> order.getServiceTypeName().getRang()
                .equals(MASTER_LOW_RANG))).reduce(0,
                (x,y) -> {return x + y.getServiceTypeName().getPrice();},
                Integer::sum);
    }

    public Integer countLowRangServiceTypes(){
        List<Order> orders = orderRepo.findAll();
        Integer lowRangServiceTypesEarning=ZERO_EARNINGS;
        for (Order order : orders) {
            if (Objects.equals(order.getServiceTypeName().getRang(), MASTER_LOW_RANG)) {
                lowRangServiceTypesEarning += order.getServiceTypeName().getPrice();
            }
        }
        return lowRangServiceTypesEarning;
    }

}
