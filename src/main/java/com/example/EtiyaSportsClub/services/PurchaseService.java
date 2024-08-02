package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.PurchaseGetDto;
import com.example.EtiyaSportsClub.entities.PurchaseEntity;
import com.example.EtiyaSportsClub.mappers.IPurchaseGetMapper;
import com.example.EtiyaSportsClub.repos.IPurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    IPurchaseRepository purchaseRepository;

    public PurchaseService(IPurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    public List<PurchaseGetDto> getAllPurchasesDto() {
        List<PurchaseEntity> purchases = purchaseRepository.findAll();
        return IPurchaseGetMapper.INSTANCE.purchasesToGetAllPurchasesDto(purchases);
    }

    public PurchaseGetDto findOnePurchaseDto(Long purchaseId) {
        Optional<PurchaseEntity> optionalPurchase = purchaseRepository.findById(purchaseId);
        if(optionalPurchase.isPresent()){
            return IPurchaseGetMapper.INSTANCE.purchaseToGetPurchaseDto(optionalPurchase.get());
        }else{
            throw new RuntimeException("Purchase not found");
        }
    }

    public PurchaseEntity createPurchase(PurchaseEntity newPurchase) {
        return purchaseRepository.save(newPurchase);
    }


    public void deletePurchase(Long purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }
}