package com.musicalEquipment.musicalEquipment.сontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping("/adminPage")
    public String getAdminPage(Model model) {
        return "adminPage";
    }
}
