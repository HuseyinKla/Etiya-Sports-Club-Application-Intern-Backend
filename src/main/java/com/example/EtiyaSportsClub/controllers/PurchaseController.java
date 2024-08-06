package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.BundlesByUserId;
import com.example.EtiyaSportsClub.dtos.BuyBundleDto;
import com.example.EtiyaSportsClub.dtos.PurchaseGetDto;
import com.example.EtiyaSportsClub.entities.PurchaseEntity;
import com.example.EtiyaSportsClub.services.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<PurchaseGetDto> getAllPurchasesDto(){
        return purchaseService.getAllPurchasesDto();
    }

    @GetMapping("/{purchaseId}")
    public PurchaseGetDto findOnePurchaseDto(@PathVariable Long purchaseId){
        return purchaseService.findOnePurchaseDto(purchaseId);
    }

    @GetMapping("/myBundles/{username}")
    public List<BundlesByUserId> findBundlesByUsername(@PathVariable String username){
        return purchaseService.findBundlesByUsername(username);
    }



    @PostMapping
    public PurchaseEntity createPurchase(@RequestBody PurchaseEntity newPurchase){
        return purchaseService.createPurchase(newPurchase);
    }

    @PostMapping("/buy")
    public ResponseEntity<PurchaseGetDto> buyBundle(@RequestBody BuyBundleDto buyBundleDto){
        PurchaseGetDto savedPurchaseDto = purchaseService.buyBundle(buyBundleDto);
        return new ResponseEntity<>(savedPurchaseDto, HttpStatus.CREATED);
    }



    @DeleteMapping("/{purchaseId}")
    public void deletePurchase(@PathVariable Long purchaseId){
        purchaseService.deletePurchase(purchaseId);
    }


}
