package com.example.EtiyaSportsClub.mappers;

import com.example.EtiyaSportsClub.dtos.responses.PromotionGetDto;
import com.example.EtiyaSportsClub.entities.PromotionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IPromotionGetMapper {
    IPromotionGetMapper INSTANCE = Mappers.getMapper(IPromotionGetMapper.class);

    @Mapping(source = "user.userName", target = "username")
    @Mapping(source = "user.name", target = "name")
    List<PromotionGetDto> promotionsToPromotionsGetDto(List<PromotionEntity> promotion);
    PromotionGetDto promotionToPromotionGetDto(PromotionEntity promotion);


}
