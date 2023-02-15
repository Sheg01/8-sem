package com.test.salon.controller;

import com.test.salon.model.Order;
import com.test.salon.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/statistics")
@PreAuthorize("hasAuthority('ADMIN')")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/charts")
    public String showCharts(Model model){

        Map<String, Integer> servicesAndCounts = statisticsService.countedServiceTypes();

        model.addAttribute("map", servicesAndCounts);
        return("charts");
    }

    @GetMapping("/earnings")
    public String showEarnings(Model model){
        List<Order> orders = statisticsService.getOrders();
        Integer lowEarnings = statisticsService.countLowRangServiceTypes();
        Integer highEarnings = statisticsService.countHighRangServiceTypes();

        model.addAttribute("lowEarnings", lowEarnings);
        model.addAttribute("highEarnings", highEarnings);
        model.addAttribute("orders", orders);

        return("earnings");
    }
}
