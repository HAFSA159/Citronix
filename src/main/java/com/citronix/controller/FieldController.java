package com.citronix.controller;

import com.citronix.dto.request.FieldRequest;
import com.citronix.dto.response.FieldResponse;
import com.citronix.service.interfaces.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fields")
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;

    @PostMapping
    public ResponseEntity<FieldResponse> createField(@Valid @RequestBody FieldRequest request) {
        FieldResponse response = fieldService.createField(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldResponse> getField(@PathVariable Long id) {
        FieldResponse response = fieldService.getFieldById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FieldResponse>> getAllFields() {
        List<FieldResponse> fields = fieldService.getAllFields();
        return ResponseEntity.ok(fields);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldResponse> updateField(@PathVariable Long id, @Valid @RequestBody FieldRequest request) {
        FieldResponse response = fieldService.updateField(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
        return ResponseEntity.noContent().build();
    }
}
