package com.example.EtiyaSportsClub.services;

import com.example.EtiyaSportsClub.dtos.BundleGetDto;
import com.example.EtiyaSportsClub.dtos.requests.BundleWithCoursesDTO;
import com.example.EtiyaSportsClub.dtos.responses.BundleCreateDto;
import com.example.EtiyaSportsClub.entities.BundleEntity;
import com.example.EtiyaSportsClub.entities.CourseEntity;
import com.example.EtiyaSportsClub.entities.UserEntity;
import com.example.EtiyaSportsClub.mappers.IBundleGetMapper;
import com.example.EtiyaSportsClub.repos.IBundleRepository;
import com.example.EtiyaSportsClub.repos.ICourseRepository;
import com.example.EtiyaSportsClub.repos.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BundleService {

    @Autowired
    IBundleRepository bundleRepository;
    @Autowired
    ICourseRepository courseRepository;
    @Autowired
    IUserRepository userRepository;

    public BundleService(IBundleRepository bundleRepository, ICourseRepository courseRepository, IUserRepository userRepository){
        this.bundleRepository = bundleRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
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
}
