package com.musicalEquipment.musicalEquipment.сontroller;

import com.musicalEquipment.musicalEquipment.model.Document;
import com.musicalEquipment.musicalEquipment.model.Service;
import com.musicalEquipment.musicalEquipment.model.ServiceType;
import com.musicalEquipment.musicalEquipment.repository.DocumentRepository;
import com.musicalEquipment.musicalEquipment.repository.ServiceRepository;
import com.musicalEquipment.musicalEquipment.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ServiceCUDController {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @GetMapping("/addService")
    public String addService(Model model) {
        Iterable<ServiceType> serviceTypes = serviceTypeRepository.findAll();
        model.addAttribute("serviceTypes", serviceTypes);
        model.addAttribute("addService", "addService");
        return "addService";
    }

    @PostMapping("/addService")
    public String addService(@RequestParam String name,
                             @RequestParam int cost,
                             @RequestParam String serviceTypeName,
                             @RequestParam String description,
                             @RequestParam MultipartFile multipartFile,
                             Model model) throws IOException {
        ServiceType serviceType = serviceTypeRepository.findByName(serviceTypeName);
        System.out.println(serviceType);
        Service service = new Service();
        service.setName(name);
        service.setServiceType(serviceType);
        service.setCost(cost);
        service.setDescription(description);
        serviceRepository.save(service);

        List<Document> uploadedDocuments = new ArrayList<>();

        uploadFile(multipartFile, service, uploadedDocuments);

        serviceRepository.save(service);
        documentRepository.saveAll(uploadedDocuments);

        return "redirect:/adminPage";
    }

    private void uploadFile(MultipartFile multipartFile, Service service, List<Document> uploadedDocuments) {
        if (multipartFile.isEmpty()) {
            return;
        }
        String fileName = multipartFile.getOriginalFilename();
        Document document = new Document();
        document.setName(fileName);

        try {
            document.setContent(multipartFile.getBytes());
        } catch (IOException e) {
        }
        documentRepository.save(document);

        service.addDocument(document);
        uploadedDocuments.add(document);
    }
}

