package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.BundleGetDto;
import com.example.EtiyaSportsClub.entities.BundleEntity;
import com.example.EtiyaSportsClub.services.BundleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bundles")
public class BundleController {

    BundleService bundleService;

    public BundleController(BundleService bundleService){
        this.bundleService = bundleService;
    }

    @GetMapping
    public List<BundleGetDto> getAllBundlesDto(){
        return bundleService.getAllBundlesDto();
    }

    @GetMapping("/{bundleId}")
    public BundleGetDto getOneBundleDto(@PathVariable Long bundleId){
        return bundleService.getOneBundleDto(bundleId);
    }

    @PostMapping
    public BundleEntity createNewBundle(@RequestBody BundleEntity newBundle){
        return bundleService.createNewBundle(newBundle);
    }



    @PutMapping("/{bundleId}")
    public BundleEntity updateBundle(@PathVariable Long bundleId, @RequestBody BundleEntity newBundle){
        return bundleService.updateBundle(bundleId, newBundle);
    }




}
