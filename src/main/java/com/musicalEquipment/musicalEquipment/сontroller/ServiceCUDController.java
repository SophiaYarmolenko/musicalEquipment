package com.musicalEquipment.musicalEquipment.сontroller;

import com.musicalEquipment.musicalEquipment.model.Document;
import com.musicalEquipment.musicalEquipment.model.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class ServiceCUDController {
    @GetMapping("/addService")
    public String addService(Model model) {
        return "addService";
    }

    @PostMapping("/addService")
    public String addService(@RequestParam String name,
                             @RequestParam String serviceSubtypeName,
                             @RequestParam int cost,
                             @RequestParam String description,
                             @RequestParam("document") MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes,
                             Model model) throws IOException {
        Service service = new Service();
        service.setName(name);
        service.setCost(cost);
        service.setDescription(description);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Document document = new Document();
        document.setName(fileName);
        document.setContent(multipartFile.getBytes());

        return "redirect:";
    }
}

