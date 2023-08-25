package pd.workshop.keycloak.service;


import pd.workshop.keycloak.model.Profile;

public interface ProfileService {
    void register(Profile profile);

    void deleteCandidate( Long profileId);




    void save(Profile profile);

    Profile getData(String profileId);

    Profile updateProfile(String id, Profile profile);
}

