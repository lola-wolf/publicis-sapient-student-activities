package com.example.springbootdbapp.model.service;
import com.example.springbootdbapp.exceptions.ProfileNotFoundException;
import com.example.springbootdbapp.model.beans.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProfileService {

    // stores profile, returns created profile
    public Profile storeProfile(Profile profile);
    // must return all the profiles in the list
    public List<Profile> fetchProfiles();
    // must return Profile or throw ProfileNotFoundException
    public Profile fetchProfile(int id) throws ProfileNotFoundException;

    public Profile changePhone(int id, long phone) throws ProfileNotFoundException;

    public Profile deleteFriend(int id, int friendId) throws ProfileNotFoundException;
}
