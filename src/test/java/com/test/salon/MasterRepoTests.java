package com.test.salon;

import com.test.salon.model.Order;
import com.test.salon.model.ServiceType;
import com.test.salon.model.User;
import com.test.salon.repository.MasterRepo;
import com.test.salon.model.Master;
import com.test.salon.repository.OrderRepo;
import com.test.salon.repository.ServiceTypeRepo;
import com.test.salon.repository.UserRepo;
import com.test.salon.service.RegistrationService;
import com.test.salon.service.ServiceTypeService;
import com.test.salon.service.StatisticsService;
import com.test.salon.service.UserService;
import com.test.salon.service.master.MasterServiceImpl;
import com.test.salon.service.order.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MasterRepoTests {

    @Mock
    private MasterRepo masterRepo;

    @InjectMocks
    private RegistrationService registrationService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private ServiceTypeService serviceTypeService;

    @Mock
    private ServiceTypeRepo serviceTypeRepo;

    @InjectMocks
    private MasterServiceImpl masterService;

    @InjectMocks
    private StatisticsService statisticsService;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepo userRepo;

    @Test
    public void masterSave() {
        Master master = new Master();
        String name = "Tony";
        String surname = "Topaz";
        Integer rang = 3;

        master.setName(name);
        master.setSurname(surname);
        master.setRang(rang);
        masterRepo.save(master);
        Assertions.assertNotNull(master.getName());
        Assertions.assertNotNull(master.getSurname());
        Assertions.assertNotNull(master.getRang());
    }

    @Test
    public void masterDelete() {
        Master master = new Master("Tony", "Topaz", 3);
        masterRepo.save(master);
        masterRepo.deleteById(master.getId());
        Assertions.assertEquals(masterRepo.findById(master.getId()), Optional.empty());
    }

    @Test
    public void registration() {
        User user = new User();
        user.setNumber("+375447789495");
        user.setMail("pashaan@gmail.com");
        user.setUsername("pavelAn");
        user.setPassword("pavelAn");
        user.setClientName("pavelAn");
        registrationService.saveUser(user);
        Assertions.assertEquals(registrationService.findUserByUsername(user), user);
    }

    @Test
    public void getOrdersByUser() {
        User user = new User();
        user.setNumber("+375447789495");
        user.setMail("pashaan@gmail.com");
        user.setUsername("pavelAn");
        user.setPassword("pavelAn");
        user.setClientName("pavelAn");
        registrationService.saveUser(user);
        Assertions.assertEquals(orderService.findOrdersByClient(user), Collections.EMPTY_LIST);
    }

    @Test
    public void saveOrder() {
        User user = new User();
        user.setNumber("+375447789495");
        user.setMail("pashaan@gmail.com");
        user.setUsername("pavelAn");
        user.setPassword("pavelAn");
        user.setClientName("pavelAn");
        registrationService.saveUser(user);

        ServiceType serviceType = new ServiceType("test", 10, "test", "test", 2);
        serviceTypeService.saveServiceType(serviceType);

        Master master = new Master();
        masterService.saveMaster(master, "test", "test", 2);

        Order save = new Order(user, serviceType, master, "2003-12-03", "12:00", "SAVE");
        orderService.saveOrder(save);

        Assertions.assertEquals(orderService.findOrdersByClient(user), List.of(save));
    }

    @Test
    public void getOrders() {
        List<Order> allOrders = orderService.findAllOrders();

        Assertions.assertEquals(allOrders, Collections.EMPTY_LIST);
    }

    @Test
    public void getMastersByRang() {
        List<Master> masters = masterService.findMasters(2);

        Assertions.assertEquals(masters, Collections.EMPTY_LIST);
    }

    @Test
    public void getStatistic() {
        Map<String, Integer> stringIntegerMap = statisticsService.countedServiceTypes();

        Assertions.assertEquals(stringIntegerMap, Collections.EMPTY_MAP);
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setNumber("+375447789495");
        user.setMail("pashaan@gmail.com");
        user.setUsername("pavelAn");
        user.setPassword("pavelAn");
        user.setClientName("pavelAn");
        registrationService.saveUser(user);

        userService.deleteUserById(user);

        Assertions.assertEquals(registrationService.findUserByUsername(user), Optional.empty());
    }


}
