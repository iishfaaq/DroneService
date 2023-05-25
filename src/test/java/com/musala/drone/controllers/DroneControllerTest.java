package com.musala.drone.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.musala.drone.enumerators.Model;
import com.musala.drone.models.Drone;
import com.musala.drone.services.DroneService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;



@RunWith(SpringRunner.class)
@WebMvcTest(DroneController.class)

public class DroneControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private DroneService droneService;
	
	@Test
	public void testCreateDrone() throws Exception{
		Drone drone = new Drone();
		drone.setSerialNumber("DR001");
		drone.setModel(Model.HEAVYWEIGHT);
		drone.setBattery(75);
		
		Mockito.when(droneService.saveDrone(Mockito.any(Drone.class))).thenReturn(drone);
		
		mockMvc.perform(post("/drone")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(drone)))
                		.andExpect(status().isCreated())
                		.andExpect(jsonPath("$.serialNumber", is("DR001")))
                		.andExpect(jsonPath("$.battery", is(75)))
                		.andExpect(jsonPath("$.weightLimit", is(500)))
                		.andExpect(jsonPath("$.model", is("HEAVYWEIGHT")))
                		.andExpect(jsonPath("$.state", is("IDLE")));
	}
	
	
	@Test
    public void testLoadMedicationToDrone() throws Exception {
        List<String> medicationCodeList = Arrays.asList("MED001", "MED002", "MED003");
        String serialNumber = "DR001";

        mockMvc.perform(put("/drone/medication/loadMedicationToDrone")
                .param("serial_number", serialNumber)
                .param("medication_code", medicationCodeList.toArray(new String[0]))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(droneService).loadMedicationToDrone(serialNumber, medicationCodeList);
    }
	
	
	private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
