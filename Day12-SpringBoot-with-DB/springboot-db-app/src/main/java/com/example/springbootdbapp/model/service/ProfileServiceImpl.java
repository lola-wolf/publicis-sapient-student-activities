package com.example.springbootdbapp.model.service;

import com.example.springbootdbapp.exceptions.ProfileNotFoundException;
import com.example.springbootdbapp.model.beans.Friend;
import com.example.springbootdbapp.model.beans.Profile;
import com.example.springbootdbapp.model.dao.FriendRepository;
import com.example.springbootdbapp.model.dao.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileDao;

    @Autowired
    private FriendRepository friendDao;

    @Transactional
    @Override
    public Profile storeProfile(Profile profile) {
        Profile createdProfile = profileDao.save(profile);
        return createdProfile;
    }

    @Override
    public List<Profile> fetchProfiles() {
        List<Profile> list = profileDao.findAll();
        return list;
    }

    @Override
    public Profile fetchProfile(int id) throws ProfileNotFoundException {
        Profile profile = profileDao.findById(id).orElse(null);

        if  (profile == null) {
            throw new ProfileNotFoundException("Profile with id " + id + " not found.");
        }
        List<Friend> friends = friendDao.getFriendsFromProfile(id);
        profile.setFriends(friends);
        return profile;
    }

    public Profile changePhone(int id, long phone) throws ProfileNotFoundException {
        Profile toChange = this.fetchProfile(id);
        toChange.setPhone(phone);
        profileDao.save(toChange);
        return toChange;
    }

    public Profile deleteFriend(int id, int friendId) throws ProfileNotFoundException {
        Profile toChange = this.fetchProfile(id);
        Friend friendToDelete = friendDao.findById(friendId).orElse(null);
        List<Friend> friendList = toChange.getFriends();
        if (friendToDelete == null) {
            throw new ProfileNotFoundException("Friend with id " + friendId + " not found.");
        }
        friendList.remove(friendToDelete);
        toChange.setFriends(friendList);
        profileDao.save(toChange);
        return toChange;
    }
}
