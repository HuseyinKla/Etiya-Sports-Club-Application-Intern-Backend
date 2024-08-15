package com.example.EtiyaSportsClub.controllers;

import com.example.EtiyaSportsClub.dtos.requests.BundleWithCoursesDTO;
import com.example.EtiyaSportsClub.dtos.responses.BundleCreateDto;
import com.example.EtiyaSportsClub.dtos.responses.BundleGetDto;
import com.example.EtiyaSportsClub.entities.BundleEntity;
import com.example.EtiyaSportsClub.entities.CourseEntity;
import com.example.EtiyaSportsClub.entities.UserEntity;
import com.example.EtiyaSportsClub.repos.IUserRepository;
import com.example.EtiyaSportsClub.services.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bundles")
public class BundleController {

    private final BundleService bundleService;
    private final IUserRepository userRepository;


    public BundleController(BundleService bundleService, IUserRepository userRepository){
        this.bundleService = bundleService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<BundleGetDto> getAllBundlesDto(){
        return bundleService.getAllBundlesDto();
    }

    @GetMapping("/allBundles/{username}")
    public List<BundleGetDto> getAllBundlesDtoByUsername(@PathVariable String username){
        return bundleService.getAllBundlesDtoByUsername(username);
    }

    @GetMapping("/{bundleId}")
    public BundleGetDto getOneBundleDto(@PathVariable Long bundleId){
        return bundleService.getOneBundleDto(bundleId);
    }

    @GetMapping("adminBundles/{username}")
    public List<BundleCreateDto> getAdminBundles(@PathVariable String username){
        return bundleService.getAdminBundles(username);
    }

    @PostMapping
    public BundleCreateDto createNewBundle(@RequestBody BundleWithCoursesDTO bundleWithCoursesDTO){
        BundleEntity newBundle = new BundleEntity();


        newBundle.setBundleName(bundleWithCoursesDTO.getBundleName());
        newBundle.setBundleDescription(bundleWithCoursesDTO.getBundleDescription());
        newBundle.setBundlePrice(bundleWithCoursesDTO.getBundlePrice());
        newBundle.setTotalLessonNumber(bundleWithCoursesDTO.getTotalLessonNumber());

        UserEntity foundedUser = userRepository.findByUserName(bundleWithCoursesDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        newBundle.setUser(foundedUser);


        List<CourseEntity> courses = bundleWithCoursesDTO.getCourses();

        return bundleService.createNewBundle(newBundle, courses);
    }



    @PutMapping("/{bundleId}")
    public BundleEntity updateBundle(@PathVariable Long bundleId, @RequestBody BundleEntity newBundle){
        return bundleService.updateBundle(bundleId, newBundle);
    }




}
