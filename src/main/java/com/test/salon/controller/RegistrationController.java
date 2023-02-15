package com.test.salon.controller;

import com.test.salon.exceptions.BadRequestException;
import com.test.salon.model.Contact;
import com.test.salon.model.User;
import com.test.salon.service.RegistrationService;
import com.test.salon.utils.RegistrationValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private RegistrationValidationService registrationValidationService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user,
                          @RequestParam String clientName,
                          @RequestParam String number,
                          @RequestParam String mail,
                          Map<String, Object> model) {

        if (mail.isEmpty()) {
            throw new BadRequestException("почта не должна быть пустой");
        }

        String validationMessage = registrationValidationService.isUserValid(user, clientName, number, mail);
        if(validationMessage!=null){
            model.put("message", validationMessage);
            return "registration";
        }

        registrationService.saveUser(user);

        Contact contact = new Contact(clientName, number, mail, user);
        registrationService.saveContact(contact);

        return "redirect:/login";
    }


}
