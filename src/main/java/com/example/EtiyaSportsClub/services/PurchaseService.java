package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.responses.BundlesByUserId;
import com.example.EtiyaSportsClub.dtos.requests.BuyBundleDto;
import com.example.EtiyaSportsClub.dtos.responses.PurchaseGetDto;
import com.example.EtiyaSportsClub.entities.BundleEntity;
import com.example.EtiyaSportsClub.entities.ProgressEntity;
import com.example.EtiyaSportsClub.entities.PurchaseEntity;
import com.example.EtiyaSportsClub.entities.UserEntity;
import com.example.EtiyaSportsClub.mappers.IPurchaseGetMapper;
import com.example.EtiyaSportsClub.repos.IBundleRepository;
import com.example.EtiyaSportsClub.repos.IProgressRepository;
import com.example.EtiyaSportsClub.repos.IPurchaseRepository;
import com.example.EtiyaSportsClub.repos.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    private final  IPurchaseRepository purchaseRepository;
    private final  IUserRepository userRepository;
    private final  IBundleRepository bundleRepository;
    private final ProgressService progressService;
    private final IProgressRepository progressRepository;
    private final  RestTemplate restTemplate;



    public PurchaseService(IPurchaseRepository purchaseRepository, IUserRepository userRepository, IBundleRepository bundleRepository, IBundleRepository bundleRepository1, ProgressService progressService, IProgressRepository progressRepository, RestTemplate restTemplate){
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.bundleRepository = bundleRepository1;
        this.progressService = progressService;
        this.progressRepository = progressRepository;
        this.restTemplate = restTemplate;
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

    @Transactional
    public PurchaseGetDto buyBundle(BuyBundleDto buyBundleDto) {
        UserEntity foundedUser = userRepository.findByUserName(buyBundleDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        BundleEntity foundedBundle = bundleRepository.findById(buyBundleDto.getBundleId())
                .orElseThrow(() -> new RuntimeException("Bundle Not Found"));

        PurchaseEntity newPurchase = new PurchaseEntity();

        newPurchase.setUser(foundedUser);
        newPurchase.setBundle(foundedBundle);
        newPurchase.setPurchaseDate(buyBundleDto.getPurchaseDate());
        newPurchase.setTotalLessonNumber(buyBundleDto.getTotalLessonNumber());

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

                        Optional<ProgressEntity> progress =  progressRepository.findByUser_UserIdAndBundle_BundleId(foundedUser.get().getUserId(), bundle.getBundle().getBundleId());
                        if (progress.isPresent()){
                            if (progress.get().getRemainingCourseNumber() == 0){
                                bundles.setProcessStatus(ProgressEntity.processStatus.FINISHED);
                            }else{
                                bundles.setProcessStatus(ProgressEntity.processStatus.PROCESSING);
                            }
                            bundles.setRemainingCourseNumber(progress.get().getRemainingCourseNumber());
                        }else{
                            bundles.setProcessStatus(ProgressEntity.processStatus.NOT);
                        }


                        return bundles;
                    })
                    .collect(Collectors.toList());
            return bundlesByUserId;
        }else{
            throw new RuntimeException("user not found");
        }


    }
}