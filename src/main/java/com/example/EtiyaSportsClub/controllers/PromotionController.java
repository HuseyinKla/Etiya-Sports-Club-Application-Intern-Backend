package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.requests.PromotionRequestDto;
import com.example.EtiyaSportsClub.dtos.responses.PromotionGetDto;
import com.example.EtiyaSportsClub.entities.PromotionEntity;
import com.example.EtiyaSportsClub.services.PromotionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }


    @GetMapping
    public List<PromotionGetDto> getAllPromotions(){
        return promotionService.getAllPromotions();
    }


    @PostMapping
    public PromotionEntity createPromotion(@RequestBody PromotionRequestDto promotionRequestDto){
        return promotionService.createPromotion(promotionRequestDto);
    }

    @DeleteMapping("/deletePromotion/{promotionId}")
    public void deletePromotion(@PathVariable Long promotionId){
        promotionService.deletePromotion(promotionId);
    }
}
