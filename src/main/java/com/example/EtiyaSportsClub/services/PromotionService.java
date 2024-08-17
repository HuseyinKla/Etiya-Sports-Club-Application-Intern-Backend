package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.requests.PromotionRequestDto;
import com.example.EtiyaSportsClub.dtos.responses.PromotionGetDto;
import com.example.EtiyaSportsClub.entities.PromotionEntity;
import com.example.EtiyaSportsClub.entities.UserEntity;
import com.example.EtiyaSportsClub.mappers.IPromotionGetMapper;
import com.example.EtiyaSportsClub.repos.IPromotionRepository;
import com.example.EtiyaSportsClub.repos.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    private final IPromotionRepository promotionRepository;
    private final IUserRepository userRepository;

    public PromotionService(IPromotionRepository promotionRepository, IUserRepository userRepository) {
        this.promotionRepository = promotionRepository;
        this.userRepository = userRepository;
    }


    public List<PromotionGetDto> getAllPromotions() {
        /*List<PromotionEntity> promotions = promotionRepository.findAll();
        List<PromotionGetDto> promotionsGetDto = (List<PromotionGetDto>) promotions.stream().map(promotion -> {
            PromotionGetDto newPromotion = new PromotionGetDto();
            newPromotion.setUsername(promotion.getUser().getUserName());
            newPromotion.setName(promotion.getUser().getName());
            newPromotion.setRequestedRoleName(promotion.getRequestedRoleName());
            return newPromotion;
        });

        return promotionsGetDto;*/

        List<PromotionEntity> promotions = promotionRepository.findAll();
        List<PromotionGetDto> promotionsGetDto = (List<PromotionGetDto>) promotions.stream().map(promotion -> {
            PromotionGetDto newPromotion = new PromotionGetDto();
            newPromotion.setUsername(promotion.getUser().getUserName());
            newPromotion.setName(promotion.getUser().getName());
            newPromotion.setRequestedRoleName(promotion.getRequestedRoleName());
            return newPromotion;
        });

        return promotionsGetDto;

    }

    public PromotionEntity createPromotion(PromotionRequestDto promotionRequestDto) {
        /*UserEntity foundedUser = userRepository.findByUserName(promotionRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        PromotionEntity newPromotion = new PromotionEntity();
        newPromotion.setUser(foundedUser);
        newPromotion.setRequestedRoleName(promotionRequestDto.getRequestedRoleName());
        promotionRepository.save(newPromotion);

        PromotionGetDto newPromotionGetDto = new PromotionGetDto();
        newPromotionGetDto.setName(foundedUser.getName());
        newPromotionGetDto.setUsername(foundedUser.getUserName());
        newPromotionGetDto.setRequestedRoleName(promotionRequestDto.getRequestedRoleName());

        return newPromotionGetDto;*/

        UserEntity foundedUser = userRepository.findByUserName(promotionRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        PromotionEntity newPromotion = new PromotionEntity();
        newPromotion.setUser(foundedUser);
        newPromotion.setRequestedRoleName(promotionRequestDto.getRequestedRoleName());
        return promotionRepository.save(newPromotion);





    }

    public void deletePromotion(Long promotionId) {
        promotionRepository.deleteById(promotionId);
    }
}
