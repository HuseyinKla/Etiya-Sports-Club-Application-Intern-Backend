package com.example.EtiyaSportsClub.mappers;

import com.example.EtiyaSportsClub.dtos.BundleGetDto;
import com.example.EtiyaSportsClub.entities.BundleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IBundleGetMapper {

    IBundleGetMapper INSTANCE = Mappers.getMapper(IBundleGetMapper.class);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "user.name", target = "name")
    BundleGetDto bundleToGetBundleDto(BundleEntity bundle);
    List<BundleGetDto> bundlesToGetAllBundlesDto(List<BundleEntity> bundles);
}