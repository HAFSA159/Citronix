package com.citronix.service.interfaces;

import com.citronix.dto.request.FieldRequest;
import com.citronix.dto.response.FieldResponse;

import java.util.List;

public interface FieldService {

    FieldResponse createField(FieldRequest request);
    FieldResponse getFieldById(Long id);
    List<FieldResponse> getAllFields();
    FieldResponse updateField(Long id, FieldRequest request);
    void deleteField(Long id);
}
