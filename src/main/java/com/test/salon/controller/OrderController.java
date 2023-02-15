package com.test.salon.controller;

import com.test.salon.dto.OrderSubmitDto;
import com.test.salon.model.Master;
import com.test.salon.model.Order;
import com.test.salon.model.ServiceType;
import com.test.salon.model.User;
import com.test.salon.service.cheque.ChequeSaveService;
import com.test.salon.service.order.OrderService;
import com.test.salon.utils.MessageGenerator;
import com.test.salon.service.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private SmtpMailSender mailSender;
    @Autowired
    private ChequeSaveService chequeSaveService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MessageGenerator messageGeneratorService;

    @GetMapping("/orders")
    public String show(@AuthenticationPrincipal User user,
                       Model model){

        List<Order> orders=orderService.findOrdersByClient(user);
        model.addAttribute("orders", orders);

        return("orderUserList");
    }

    @GetMapping("/editOrders")
    public String editOrders(Model model){

        List<Order> orders=orderService.findAllOrders();
        model.addAttribute("orders", orders);

        return("orderAdminPage");
    }

    @GetMapping
    public String main(Model model) {

        HashSet<String> serviceGroups=orderService.findServiceGroups();

        model.addAttribute("serviceGroups", serviceGroups);

        return "orders";
    }

    @GetMapping("/details")
    public String detail(@RequestParam String serviceGroup,
                         Model model){

        List<ServiceType> serviceTypes = orderService.findByServiceGroups(serviceGroup);

        model.addAttribute("serviceTypes", serviceTypes);
        model.addAttribute("serviceGroup", serviceGroup);

        return("orderDetails");
    }

    @GetMapping("/chooseMaster")
    public String chooseMaster(@RequestParam String serviceGroup,
                               @RequestParam String serviceType,
                               @RequestParam String date,
                               @RequestParam String time,
                               Model model){

        List<ServiceType> serviceTypes = orderService.findTypesByName(serviceType);
        ServiceType currentService = serviceTypes.get(0);
        Integer currentRang = currentService.getRang();
        List<Master> masters = orderService.findMastersByRang(currentRang);


        model.addAttribute("serviceType", serviceType);
        model.addAttribute("serviceGroup", serviceGroup);
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("masters", masters);

        return("orderSubmit");
    }

    @PostMapping("/submit")
    public String submit(
            @AuthenticationPrincipal User user,
            OrderSubmitDto orderSubmitDto) {

        String status = "processing";
        List<ServiceType> serviceTypes = orderService.findTypesByName(orderSubmitDto.getServiceType());
        ServiceType currentService = serviceTypes.get(0);

        List<Master> masters = orderService.findMasterByName(orderSubmitDto.getMaster());
        Master currentMaster = masters.get(0);

        Order order = new Order(user, currentService, currentMaster, orderSubmitDto.getDate(), orderSubmitDto.getTime(), status);

        String message = messageGeneratorService.mailMessageGenerate(user, order);

        String chequeText = messageGeneratorService.chequeMessageGenerate(user, order);

        //mailSender.send(user.getMail(),"Запись оформлена", message);

        orderService.saveOrder(order);

        chequeSaveService.saveCheque(chequeText, order.getId());

        return ("orderSuccess");
    }

}
