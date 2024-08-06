package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.BundlesByUserId;
import com.example.EtiyaSportsClub.dtos.BuyBundleDto;
import com.example.EtiyaSportsClub.dtos.PurchaseGetDto;
import com.example.EtiyaSportsClub.entities.BundleEntity;
import com.example.EtiyaSportsClub.entities.PurchaseEntity;
import com.example.EtiyaSportsClub.entities.UserEntity;
import com.example.EtiyaSportsClub.mappers.IPurchaseGetMapper;
import com.example.EtiyaSportsClub.repos.IBundleRepository;
import com.example.EtiyaSportsClub.repos.IPurchaseRepository;
import com.example.EtiyaSportsClub.repos.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private IPurchaseRepository purchaseRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBundleRepository bundleRepository;

    public PurchaseService(IPurchaseRepository purchaseRepository, IUserRepository userRepository, IBundleRepository bundleRepository){
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
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

    public PurchaseGetDto buyBundle(BuyBundleDto buyBundleDto) {
        UserEntity foundedUser = userRepository.findByUserName(buyBundleDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        BundleEntity foundedBundle = bundleRepository.findById(buyBundleDto.getBundleId())
                .orElseThrow(() -> new RuntimeException("Bundle Not Found"));

        PurchaseEntity newPurchase = new PurchaseEntity();

        newPurchase.setUser(foundedUser);
        newPurchase.setBundle(foundedBundle);
        newPurchase.setPurchaseDate(buyBundleDto.getPurchaseDate());

        PurchaseEntity savedPurchase = purchaseRepository.save(newPurchase);
        return IPurchaseGetMapper.INSTANCE.purchaseToGetPurchaseDto(savedPurchase);
    }



    public void deletePurchase(Long purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }

    public List<BundlesByUserId> findBundlesByUsername(String username) {
        Optional<UserEntity> foundedUser = userRepository.findByUserName(username);
        if (foundedUser.isPresent()){
            List<PurchaseEntity> allPurchasesByUsername = purchaseRepository.findByUser_UserId(foundedUser.get().getUserId());
            List<BundlesByUserId> bundlesByUserId = (List<BundlesByUserId>) allPurchasesByUsername.stream()
                    .map(bundle -> {
                        BundlesByUserId bundles = new BundlesByUserId();
                        bundles.setBundleId(bundle.getBundle().getBundleId());
                        bundles.setBundleName(bundle.getBundle().getBundleName());
                        bundles.setBundlePrice(bundle.getBundle().getBundlePrice());
                        bundles.setBundleDescription(bundle.getBundle().getBundleDescription());
                        bundles.setTotalLessonNumber(bundle.getBundle().getTotalLessonNumber());
                        bundles.setPurchaseDate(bundle.getPurchaseDate());
                        return bundles;
                    })
                    .collect(Collectors.toList());
            return bundlesByUserId;
        }else{
            throw new RuntimeException("user not found");
        }


    }
}