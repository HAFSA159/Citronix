package com.citronix.controller;

import com.citronix.dto.response.TreeResponse;
import com.citronix.dto.request.TreeRequest;
import com.citronix.service.interfaces.TreeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trees")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping
    public ResponseEntity<TreeResponse> createTree(@RequestBody TreeRequest treeRequest) {
        TreeResponse createdTree = treeService.createTree(treeRequest);
        return new ResponseEntity<>(createdTree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreeResponse> getTreeById(@PathVariable Long id) {
        TreeResponse treeResponse = treeService.findTreeById(id);
        if (treeResponse != null) {
            return new ResponseEntity<>(treeResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<TreeResponse>> getAllTrees() {
        List<TreeResponse> treeResponses = treeService.getAllTrees();
        return new ResponseEntity<>(treeResponses, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TreeResponse> updateTree(@PathVariable Long id, @RequestBody TreeRequest treeRequest) {
        TreeResponse updatedTree = treeService.updateTree(id, treeRequest);
        if (updatedTree != null) {
            return new ResponseEntity<>(updatedTree, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTree(@PathVariable Long id) {
        boolean deleted = treeService.deleteTree(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}