package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.responses.BundleCreateDto;
import com.example.EtiyaSportsClub.dtos.responses.BundleGetDto;
import com.example.EtiyaSportsClub.entities.BundleEntity;
import com.example.EtiyaSportsClub.entities.CourseEntity;
import com.example.EtiyaSportsClub.entities.PurchaseEntity;
import com.example.EtiyaSportsClub.entities.UserEntity;
import com.example.EtiyaSportsClub.mappers.IBundleGetMapper;
import com.example.EtiyaSportsClub.repos.IBundleRepository;
import com.example.EtiyaSportsClub.repos.ICourseRepository;
import com.example.EtiyaSportsClub.repos.IPurchaseRepository;
import com.example.EtiyaSportsClub.repos.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BundleService {

    private final IBundleRepository bundleRepository;
    private final ICourseRepository courseRepository;
    private final IUserRepository userRepository;
    private final IPurchaseRepository purchaseRepository;



    public BundleService(IBundleRepository bundleRepository, ICourseRepository courseRepository, IUserRepository userRepository, IPurchaseRepository purchaseRepository){
        this.bundleRepository = bundleRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public List<BundleGetDto> getAllBundlesDto() {
        List<BundleEntity> bundles = bundleRepository.findAll();
        return IBundleGetMapper.INSTANCE.bundlesToGetAllBundlesDto(bundles);


    }

    public BundleGetDto getOneBundleDto(Long bundleId) {
        Optional<BundleEntity> bundleOptional = bundleRepository.findById(bundleId);
        if (bundleOptional.isPresent()){
            return IBundleGetMapper.INSTANCE.bundleToGetBundleDto(bundleOptional.get());
        }else{
            throw new RuntimeException("Bundle not found");
        }

    }


    public BundleEntity updateBundle(Long bundleId, BundleEntity newBundle) {
        Optional<BundleEntity> bundle = bundleRepository.findById(bundleId);
        if(bundle.isPresent()){
            BundleEntity foundedBundle = bundle.get();
            foundedBundle.setBundlePrice(newBundle.getBundlePrice());
            bundleRepository.save(foundedBundle);
            return foundedBundle;
        }
        return null;
    }

    public BundleCreateDto createNewBundle(BundleEntity newBundle, List<CourseEntity> courses) {


        BundleEntity savedBundle = bundleRepository.save(newBundle);

        for (CourseEntity course : courses) {
            course.setBundle(savedBundle);
            courseRepository.save(course);
        }

        savedBundle.setCourses(courses);
        bundleRepository.save(savedBundle);




        return IBundleGetMapper.INSTANCE.bundleToBundleCreateDto(savedBundle);
    }

    public List<BundleCreateDto> getAdminBundles(String username) {
        UserEntity foundedUser = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<BundleEntity> foundedBundles = bundleRepository.findByUser_UserId(foundedUser.getUserId());
        return IBundleGetMapper.INSTANCE.bundlesToBundlesCreateDto(foundedBundles);
    }

    public List<BundleGetDto> getAllBundlesDtoByUsername(String username) {
        List<BundleEntity> allBundles = bundleRepository.findAll();
        UserEntity foundedUser = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        List<PurchaseEntity> purchasedBundles  = purchaseRepository.findByUser_UserId(foundedUser.getUserId());

        List<Long> purchasedBundleIds = purchasedBundles.stream()
                .map(purchase -> purchase.getBundle().getBundleId())
                .collect(Collectors.toList());

        List<BundleEntity> availableBundles = allBundles.stream()
                .filter(bundle -> !purchasedBundleIds.contains(bundle.getBundleId()))
                .collect(Collectors.toList());
        return IBundleGetMapper.INSTANCE.bundlesToGetAllBundlesDto(availableBundles);
    }
}
