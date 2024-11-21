package com.citronix.service.impl;

import com.citronix.dto.request.HarvestDetailRequest;
import com.citronix.dto.response.HarvestDetailResponse;
import com.citronix.entity.Harvest;
import com.citronix.entity.HarvestDetail;
import com.citronix.entity.Tree;
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

    public HarvestDetailServiceImpl(HarvestDetailRepository harvestDetailRepository,
                                    HarvestRepository harvestRepository,
                                    TreeRepository treeRepository) {
        this.harvestDetailRepository = harvestDetailRepository;
        this.harvestRepository = harvestRepository;
        this.treeRepository = treeRepository;
    }

    @Override
    public HarvestDetailResponse findById(Long id) {
        HarvestDetail harvestDetail = harvestDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HarvestDetail not found"));
        return mapToResponse(harvestDetail);
    }

    @Override
    public List<HarvestDetailResponse> findAll() {
        List<HarvestDetail> harvestDetails = harvestDetailRepository.findAll();
        return harvestDetails.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HarvestDetailResponse create(HarvestDetailRequest request) {
        Harvest harvest = harvestRepository.findById(request.getHarvestId())
                .orElseThrow(() -> new RuntimeException("Harvest not found"));
        Tree tree = treeRepository.findById(request.getTreeId())
                .orElseThrow(() -> new RuntimeException("Tree not found"));
        HarvestDetail harvestDetail = new HarvestDetail(null, request.getQuantity(), harvest, tree);
        harvestDetail = harvestDetailRepository.save(harvestDetail);
        return mapToResponse(harvestDetail);
    }

    @Override
    public HarvestDetailResponse update(Long id, HarvestDetailRequest request) {
        HarvestDetail harvestDetail = harvestDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HarvestDetail not found"));

        Harvest harvest = harvestRepository.findById(request.getHarvestId())
                .orElseThrow(() -> new RuntimeException("Harvest not found"));
        Tree tree = treeRepository.findById(request.getTreeId())
                .orElseThrow(() -> new RuntimeException("Tree not found"));

        harvestDetail.setQuantity(request.getQuantity());
        harvestDetail.setHarvest(harvest);
        harvestDetail.setTree(tree);

        harvestDetail = harvestDetailRepository.save(harvestDetail);
        return mapToResponse(harvestDetail);
    }

    @Override
    public boolean delete(Long id) {
        if (harvestDetailRepository.existsById(id)) {
            harvestDetailRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private HarvestDetailResponse mapToResponse(HarvestDetail harvestDetail) {
        return HarvestDetailResponse.builder()
                .id(harvestDetail.getId())
                .quantity(harvestDetail.getQuantity())
                .harvestId(harvestDetail.getHarvest().getId())
                .treeId(harvestDetail.getTree().getId())
                .build();
    }
}
