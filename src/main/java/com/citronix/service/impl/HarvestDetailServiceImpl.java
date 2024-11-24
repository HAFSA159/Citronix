package com.citronix.service.impl;

import com.citronix.dto.request.HarvestDetailRequest;
import com.citronix.dto.response.HarvestDetailResponse;
import com.citronix.entity.Harvest;
import com.citronix.entity.HarvestDetail;
import com.citronix.entity.Tree;
import com.citronix.mapper.HarvestDetailMapper;
import com.citronix.repository.HarvestDetailRepository;
import com.citronix.repository.HarvestRepository;
import com.citronix.repository.TreeRepository;
import com.citronix.service.interfaces.HarvestDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestDetailServiceImpl implements HarvestDetailService {

    private final HarvestDetailRepository harvestDetailRepository;
    private final HarvestRepository harvestRepository;
    private final TreeRepository treeRepository;
    private final HarvestDetailMapper harvestDetailMapper;

    public HarvestDetailServiceImpl(HarvestDetailRepository harvestDetailRepository, HarvestRepository harvestRepository, TreeRepository treeRepository, HarvestDetailMapper harvestDetailMapper) {
        this.harvestDetailRepository = harvestDetailRepository;
        this.harvestRepository = harvestRepository;
        this.treeRepository = treeRepository;
        this.harvestDetailMapper = harvestDetailMapper;
    }

    @Override
    public HarvestDetailResponse findById(Long id) {
        HarvestDetail harvestDetail = harvestDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HarvestDetail not found"));
        return harvestDetailMapper.toDTO(harvestDetail);
    }

    @Override
    public List<HarvestDetailResponse> findAll() {
        List<HarvestDetail> harvestDetails = harvestDetailRepository.findAll();
        return harvestDetails.stream()
                .map(harvestDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HarvestDetailResponse create(HarvestDetailRequest request) {
        Harvest harvest = harvestRepository.findById(request.getHarvestId())
                .orElseThrow(() -> new RuntimeException("Harvest not found"));
        Tree tree = treeRepository.findById(request.getTreeId())
                .orElseThrow(() -> new RuntimeException("Tree not found"));

        HarvestDetail harvestDetail = harvestDetailMapper.toEntity(
                HarvestDetailResponse.builder()
                        .quantity(request.getQuantity())
                        .harvestId(harvest.getId())
                        .treeId(tree.getId())
                        .build()
        );

        harvestDetail = harvestDetailRepository.save(harvestDetail);
        return harvestDetailMapper.toDTO(harvestDetail);
    }

    @Override
    public HarvestDetailResponse update(Long id, HarvestDetailRequest request) {
        HarvestDetail existingHarvestDetail = harvestDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HarvestDetail not found"));

        Harvest harvest = harvestRepository.findById(request.getHarvestId())
                .orElseThrow(() -> new RuntimeException("Harvest not found"));
        Tree tree = treeRepository.findById(request.getTreeId())
                .orElseThrow(() -> new RuntimeException("Tree not found"));

        HarvestDetailResponse updatedHarvestDetailDTO = HarvestDetailResponse.builder()
                .id(existingHarvestDetail.getId())
                .quantity(request.getQuantity())
                .harvestId(harvest.getId())
                .treeId(tree.getId())
                .build();

        harvestDetailMapper.updateEntityFromDTO(updatedHarvestDetailDTO, existingHarvestDetail);

        HarvestDetail updatedHarvestDetail = harvestDetailRepository.save(existingHarvestDetail);
        return harvestDetailMapper.toDTO(updatedHarvestDetail);
    }

    @Override
    public boolean delete(Long id) {
        if (harvestDetailRepository.existsById(id)) {
            harvestDetailRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
