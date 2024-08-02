package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.BundleGetDto;
import com.example.EtiyaSportsClub.entities.BundleEntity;
import com.example.EtiyaSportsClub.mappers.IBundleGetMapper;
import com.example.EtiyaSportsClub.repos.IBundleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BundleService {

    IBundleRepository bundleRepository;

    public BundleService(IBundleRepository bundleRepository){
        this.bundleRepository = bundleRepository;
    }

    public List<BundleGetDto> getAllBundlesDto() {
        List<BundleEntity> bundles = bundleRepository.findAll();
        return IBundleGetMapper.INSTANCE.bundlesToGetAllBundlesDto(bundles);


    }

    public BundleGetDto getOneBundleDto(Long bundleId) {
        Optional<BundleEntity> bundleOptional = bundleRepository.findById(bundleId);
        if (bundleOptional.isPresent()){
            return IBundleGetMapper.INSTANCE.bundleToGetBundleDto(bundleOptional.get());
        }else{
            throw new RuntimeException("Bundle not found");
        }

    }


    public BundleEntity updateBundle(Long bundleId, BundleEntity newBundle) {
        Optional<BundleEntity> bundle = bundleRepository.findById(bundleId);
        if(bundle.isPresent()){
            BundleEntity foundedBundle = bundle.get();
            foundedBundle.setBundlePrice(newBundle.getBundlePrice());
            bundleRepository.save(foundedBundle);
            return foundedBundle;
        }
        return null;
    }

    public BundleEntity createNewBundle(BundleEntity newBundle) {
        return bundleRepository.save(newBundle);
    }
}
