package com.example.EtiyaSportsClub.mappers;

import com.example.EtiyaSportsClub.dtos.PurchaseGetDto;
import com.example.EtiyaSportsClub.entities.PurchaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IPurchaseGetMapper {

    IPurchaseGetMapper INSTANCE = Mappers.getMapper(IPurchaseGetMapper.class);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "bundle.bundleId", target = "bundleId")
    @Mapping(source = "bundle.bundleName", target = "bundleName")
    @Mapping(source = "bundle.bundlePrice", target = "bundlePrice")
    PurchaseGetDto purchaseToGetPurchaseDto(PurchaseEntity purchase);
    List<PurchaseGetDto> purchasesToGetAllPurchasesDto(List<PurchaseEntity> purchases);
}
