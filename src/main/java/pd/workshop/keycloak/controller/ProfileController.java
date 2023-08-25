package pd.workshop.keycloak.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pd.workshop.keycloak.model.Profile;
import pd.workshop.keycloak.repository.ProfileRepository;
import pd.workshop.keycloak.service.ProfileService;

import javax.annotation.security.RolesAllowed;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping
public class ProfileController {
    private final ProfileService profileService;


    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;

    }

@GetMapping("/profile-register")
@RolesAllowed("user")
public ResponseEntity<Profile> getProfileDetailsByName(@RequestParam String profileName) {
    Profile profile = profileRepository.findByName(profileName);
    if (profile != null) {
        return ResponseEntity.ok(profile);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @GetMapping("/profile-register-all")
    @RolesAllowed("admin")
    public ResponseEntity<List<Profile>> getProfileDetails() {
        List<Profile> profileList= profileRepository.findAll();
        ResponseEntity<Profile> responseEntity=new ResponseEntity<>(HttpStatus.OK);
        System.out.println(responseEntity.getHeaders());
        return new ResponseEntity<>(profileList, HttpStatus.ACCEPTED);
    }

    @PostMapping("/profile-add")
    @RolesAllowed("admin")
    public Profile createProfile(@RequestBody Profile profile){
        return profileRepository.save(profile);
    }



    /**
     * Deletes a candidate profile by ID
     *
     * @param profileId
     * @return
     */
    @DeleteMapping("/profile/{profileId}")
    @RolesAllowed("admin")
    public ResponseEntity<String> deleteProfile(@PathVariable Long profileId) {
        profileService.deleteCandidate(profileId);
        return ResponseEntity.ok("Profile deleted successfully");
    }

    @PutMapping("/update-profile/{id}")
    @RolesAllowed("admin")
    public ResponseEntity<String> updateProfile(@PathVariable String id ,@RequestBody Profile profile){
        Profile updateProfile = profileService.updateProfile(id,profile);
        if(updateProfile != null) {

            return ResponseEntity.ok("Profile updated");
        }else{
            return ResponseEntity.ok("Sorry! Profile not updated");
        }
    }
}
