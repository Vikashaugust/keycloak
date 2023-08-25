package pd.workshop.keycloak.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pd.workshop.keycloak.model.Profile;
import pd.workshop.keycloak.repository.ProfileRepository;


@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public void register( Profile profile) {
        profileRepository.save(profile);

    }

    @Override
    public void deleteCandidate(Long profileId) {
        profileRepository.deleteByprofileId(profileId);
    }




    public Profile updateProfile(String id, Profile profile) {

        if(profileRepository.existsById(id)) {
            profile.setId(id);
            return profileRepository.save(profile);
        }else{
            return null;
        }
    }

    public Profile getData(String id){
        return profileRepository.findAll().stream().filter(profile->profile.getId().equals(id)).findFirst().get();
    }



    public void save(Profile profile) {
        profileRepository.save(profile);
    }
}

