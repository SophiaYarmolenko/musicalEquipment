package com.musicalEquipment.musicalEquipment.сontroller;

import com.musicalEquipment.musicalEquipment.model.Service;
import com.musicalEquipment.musicalEquipment.model.ServiceType;
import com.musicalEquipment.musicalEquipment.repository.ServiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ServiceControllerTest {
    @MockBean
    private ServiceRepository serviceRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        Service service = new Service(34L, "Колонка1", 400, "description", new ServiceType(), Collections.emptyList());
        when(serviceRepository.findById(34L)).thenReturn(Optional.of(service));
    }

    @Test
    void getServices() throws Exception {
        List<Service> services = Arrays.asList(
                new Service(34L, "Колонка1", 400, "description", new ServiceType(), Collections.emptyList()));

        when(serviceRepository.findAll()).thenReturn(services);

        mockMvc.perform(get("/services"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));


        verify(serviceRepository, times(1)).findAll();
    }

    @Test
    void getService() throws Exception {
        mockMvc.perform(get("/services/34"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        verify(serviceRepository, times(1)).findById(34L);
    }

    @Test
    void deleteService() throws Exception {
        doNothing().when(serviceRepository).deleteById(34L);

        mockMvc.perform(delete("/services/34"))
                .andExpect(status().isOk());

        verify(serviceRepository, times(1)).deleteById(34L);
    }
}