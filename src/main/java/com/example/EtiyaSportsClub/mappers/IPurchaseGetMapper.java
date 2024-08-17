package com.example.EtiyaSportsClub.mappers;

import com.example.EtiyaSportsClub.dtos.responses.BundlesByUserId;
import com.example.EtiyaSportsClub.dtos.requests.BuyBundleDto;
import com.example.EtiyaSportsClub.dtos.responses.PurchaseGetDto;
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
    @Mapping(source = "bundle.totalLessonNumber", target = "totalLessonNumber")
    PurchaseGetDto purchaseToGetPurchaseDto(PurchaseEntity purchase);
    List<PurchaseGetDto> purchasesToGetAllPurchasesDto(List<PurchaseEntity> purchases);

    PurchaseGetDto purchasePostDto(BuyBundleDto bundleDto);
    PurchaseEntity purchaseGetDtoToPurchaseEntity(PurchaseGetDto purchaseGetDto);





    @Mapping(source = "bundle.bundleId", target = "bundleId")
    @Mapping(source = "bundle.bundleName", target = "bundleName")
    @Mapping(source = "bundle.bundlePrice", target = "bundlePrice")
    @Mapping(source = "bundle.bundleDescription", target = "bundleDescription")
    @Mapping(source = "bundle.totalLessonNumber", target = "totalLessonNumber")
    @Mapping(source = "bundle.purchaseDate", target = "purchaseDate")
    List<BundlesByUserId> bundlesByUserIdDto(List<PurchaseEntity> purchaseEntity);

}
