package com.citronix.citronix;

import com.citronix.dto.request.FarmRequest;
import com.citronix.dto.response.FarmResponse;
import com.citronix.entity.Farm;
import com.citronix.entity.Field;
import com.citronix.entity.enums.Season;
import com.citronix.mapper.FarmMapper;
import com.citronix.repository.FarmRepository;
import com.citronix.service.impl.FarmServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FarmServiceImplTest {

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FarmMapper farmMapper;

    @InjectMocks
    private FarmServiceImpl farmService;

    private Farm farm;
    private FarmRequest farmRequest;
    private FarmResponse farmResponse;

    @BeforeEach
    public void setUp() {
        farm = Farm.builder()
                .id(1L)
                .name("Test Farm")
                .area(100.0)
                .location("Test Location")
                .build();

        farmRequest = new FarmRequest();
        farmRequest.setName("Updated Farm");
        farmRequest.setArea(120.0);
        farmRequest.setLocation("Updated Location");

        farmResponse = new FarmResponse();
        farmResponse.setId(1L);
        farmResponse.setName("Test Farm");
        farmResponse.setArea(100.0);
        farmResponse.setLocation("Test Location");
    }

    @Test
    public void testCreateFarm() {
        // Scénario : Création d'une ferme avec succès
        when(farmRepository.save(any(Farm.class))).thenReturn(farm);
        when(farmMapper.toDTO(any(Farm.class))).thenReturn(farmResponse);

        FarmResponse result = farmService.createFarm(farmRequest);

        assertNotNull(result);
        assertEquals(farmResponse.getId(), result.getId());
        verify(farmRepository).save(any(Farm.class));
    }

    @Test
    public void testGetFarmById() {
        // Scénario : Récupération d'une ferme existante
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(farmMapper.toDTO(farm)).thenReturn(farmResponse);

        FarmResponse result = farmService.getFarmById(1L);

        assertNotNull(result);
        assertEquals(farmResponse.getId(), result.getId());
    }

    @Test
    public void testGetFarmByIdNotFound() {
        // Scénario : Tentative de récupération d'une ferme inexistante
        when(farmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            farmService.getFarmById(1L);
        });
    }

    @Test
    public void testGetAllFarms() {
        // Scénario : Récupération de la liste des fermes
        List<Farm> farms = Arrays.asList(farm);
        when(farmRepository.findAll()).thenReturn(farms);
        when(farmMapper.toDTO(farm)).thenReturn(farmResponse);

        List<FarmResponse> results = farmService.getAllFarms();

        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    public void testUpdateFarm() {
        // Scénario : Mise à jour d'une ferme existante
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(farmRepository.save(any(Farm.class))).thenReturn(farm);
        when(farmMapper.toDTO(any(Farm.class))).thenReturn(farmResponse);

        FarmResponse result = farmService.updateFarm(1L, farmRequest);

        assertNotNull(result);
        assertEquals(farmResponse.getId(), result.getId());
        verify(farmRepository).save(any(Farm.class));
    }

    @Test
    public void testDeleteFarm() {
        // Scénario : Suppression d'une ferme existante
        when(farmRepository.existsById(1L)).thenReturn(true);

        farmService.deleteFarm(1L);

        verify(farmRepository).deleteById(1L);
    }

    @Test
    public void testDeleteFarmNotFound() {
        // Scénario : Tentative de suppression d'une ferme inexistante
        when(farmRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            farmService.deleteFarm(1L);
        });
    }

    @Test
    public void testValidateFieldsArea_ValidArea() {
        // Scénario : Validation d'une ferme avec une zone de champs valide
        farm.setFields(Arrays.asList(
                Field.builder().area(50.0).build(),
                Field.builder().area(40.0).build()
        ));

        assertTrue(farmService.validateFieldsArea(farm));
    }

    @Test
    public void testValidateFieldsArea_InvalidArea() {
        // Scénario : Validation d'une ferme avec une zone de champs invalide
        farm.setFields(Arrays.asList(
                Field.builder().area(60.0).build(),
                Field.builder().area(50.0).build()
        ));

        assertFalse(farmService.validateFieldsArea(farm));
    }
}