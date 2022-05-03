package com.zemnov.salon.controller;

import com.zemnov.salon.model.Master;
import com.zemnov.salon.model.User;
import com.zemnov.salon.repository.MasterRepo;
import com.zemnov.salon.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/masters")
public class MasterController {
    @Autowired
    private MasterService masterService;

    @GetMapping
    public String main(@RequestParam(required = false, defaultValue = "1") Integer rang,  Model model) {

        List<Master> masters = masterService.findMasters(rang);
        model.addAttribute("masters", masters);

        return "masters";
    }

    @GetMapping("{master}/edit")
    public String masterEditForm(@PathVariable Master master, Model model) {
        model.addAttribute("master", master);
        return "masterEdit";
    }

    @GetMapping("{master}/delete")
    public String masterDeleteForm(@PathVariable Master master, Model model) {
        model.addAttribute("master", master);

        return "masterDelete";
    }

    @PostMapping("/edit")
    public String masterSave(
            @RequestParam("masterId") Master master,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Integer rang, Map<String, Object> model)
    {
        masterService.masterSave(master, name, surname, rang);
        return ("redirect:/masters");
    }

    @PostMapping("/delete")
    public String masterDelete(
            @RequestParam("masterId") Master master
            )
    {
        masterService.masterDelete(master);
        return ("redirect:/masters");
    }


    @PostMapping
    public String add(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Integer rang){

        masterService.masterAdd(name, surname, rang);

        return "redirect:/masters";
    }

}
