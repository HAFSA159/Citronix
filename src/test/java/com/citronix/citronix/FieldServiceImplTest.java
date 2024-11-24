package com.citronix.citronix;

import com.citronix.dto.request.FieldRequest;
import com.citronix.dto.response.FieldResponse;
import com.citronix.dto.response.TreeResponse;
import com.citronix.entity.Field;
import com.citronix.entity.Farm;
import com.citronix.mapper.FieldMapper;
import com.citronix.repository.FieldRepository;
import com.citronix.repository.FarmRepository;
import com.citronix.service.impl.FieldServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FieldServiceImplTest {

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FieldMapper fieldMapper;

    @InjectMocks
    private FieldServiceImpl fieldService;

    private Field field;
    private Farm farm;
    private FieldRequest fieldRequest;
    private FieldResponse fieldResponse;

    @BeforeEach
    public void setUp() {
        farm = Farm.builder()
                .id(1L)
                .name("Test Farm")
                .area(100.0)
                .build();

        field = Field.builder()
                .id(1L)
                .area(10.0)
                .farm(farm)
                .build();

        fieldRequest = new FieldRequest();
        fieldRequest.setArea(10.0);
        fieldRequest.setFarmId(1L);

        List<TreeResponse> trees = new ArrayList<>();

        fieldResponse = new FieldResponse(1L, 10.0, 1L, trees);
    }

    @Test
    public void testCreateField_Success() {
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(fieldRepository.countByFarmId(1L)).thenReturn(5L);
        when(fieldRepository.sumFieldAreaByFarmId(1L)).thenReturn(40.0);
        when(fieldRepository.save(any(Field.class))).thenReturn(field);

        when(fieldMapper.toDTO(field)).thenReturn(new FieldResponse(
                field.getId(),
                field.getArea(),
                field.getFarm().getId(),
                new ArrayList<>()
        ));

        FieldResponse result = fieldService.createField(fieldRequest);

        assertNotNull(result);
        assertEquals(10.0, result.getArea());
        verify(fieldRepository).save(any(Field.class));
    }

    @Test
    public void testCreateField_AreaTooSmall() {
        // Scénario : Tentative de création d'un champ avec une zone trop petite
        fieldRequest.setArea(0.05);

        assertThrows(RuntimeException.class, () -> {
            fieldService.createField(fieldRequest);
        }, "Field area must be at least 0.1 units.");
    }

    @Test
    public void testCreateField_FarmNotFound() {
        // Scénario : Tentative de création d'un champ avec une ferme inexistante
        when(farmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            fieldService.createField(fieldRequest);
        }, "Farm with ID 1 not found");
    }

    @Test
    public void testCreateField_TooManyFields() {
        // Scénario : Tentative de création d'un champ quand la ferme a déjà 10 champs
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(fieldRepository.countByFarmId(1L)).thenReturn(10L);

        assertThrows(RuntimeException.class, () -> {
            fieldService.createField(fieldRequest);
        }, "Farm cannot have more than 10 fields");
    }

    @Test
    public void testCreateField_ExceedingRemainingArea() {
        // Scénario : Tentative de création d'un champ dépassant la zone disponible
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(fieldRepository.countByFarmId(1L)).thenReturn(5L);
        when(fieldRepository.sumFieldAreaByFarmId(1L)).thenReturn(55.0);

        assertThrows(RuntimeException.class, () -> {
            fieldService.createField(fieldRequest);
        }, "Cannot add field. Remaining available area is");
    }

    @Test
    public void testGetFieldById_Success() {
        // Scénario : Récupération d'un champ existant
        when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));
        when(fieldMapper.toDTO(field)).thenReturn(fieldResponse);

        FieldResponse result = fieldService.getFieldById(1L);

        assertNotNull(result);
        assertEquals(10.0, result.getArea());
    }

    @Test
    public void testGetFieldById_NotFound() {
        // Scénario : Tentative de récupération d'un champ inexistant
        when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            fieldService.getFieldById(1L);
        }, "Field with ID 1 not found");
    }

    @Test
    public void testGetAllFields() {
        List<Field> fields = Arrays.asList(field);
        when(fieldRepository.findAll()).thenReturn(fields);
        when(fieldMapper.toDTO(field)).thenReturn(fieldResponse);

        List<FieldResponse> results = fieldService.getAllFields();

        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    public void testUpdateField_Success() {
        // Scénario : Mise à jour d'un champ existant
        when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(fieldRepository.save(any(Field.class))).thenReturn(field);
        when(fieldMapper.toDTO(field)).thenReturn(fieldResponse);

        FieldResponse result = fieldService.updateField(1L, fieldRequest);

        assertNotNull(result);
        assertEquals(10.0, result.getArea());
        verify(fieldRepository).save(any(Field.class));
    }

    @Test
    public void testUpdateField_FieldNotFound() {
        // Scénario : Tentative de mise à jour d'un champ inexistant
        when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            fieldService.updateField(1L, fieldRequest);
        }, "Field with ID 1 not found");
    }

    @Test
    public void testUpdateField_FarmNotFound() {
        // Scénario : Tentative de mise à jour avec une ferme inexistante
        when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));
        when(farmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            fieldService.updateField(1L, fieldRequest);
        }, "Farm with ID 1 not found");
    }

    @Test
    public void testDeleteField_Success() {
        // Scénario : Suppression d'un champ existant
        when(fieldRepository.existsById(1L)).thenReturn(true);

        fieldService.deleteField(1L);

        verify(fieldRepository).deleteById(1L);
    }

    @Test
    public void testDeleteField_NotFound() {
        // Scénario : Tentative de suppression d'un champ inexistant
        when(fieldRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            fieldService.deleteField(1L);
        }, "Field with ID 1 not found");
    }
}