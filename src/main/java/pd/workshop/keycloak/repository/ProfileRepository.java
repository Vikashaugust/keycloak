package pd.workshop.keycloak.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pd.workshop.keycloak.model.Profile;


import java.util.Optional;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

    void deleteByprofileId(Long profileId);

    Profile findByProfileId(Long profileId);
    Optional<Profile> findById(String id);

    Profile findByName(String profileName);


}

