package com.example.springbootdbapp.model.service;

import com.example.springbootdbapp.exceptions.ProfileNotFoundException;
import com.example.springbootdbapp.model.beans.Friend;
import com.example.springbootdbapp.model.dao.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendDao;

    @Override
    public Friend addFriend(int profileIdRef, Friend friend) {
        // will not throw an exception if profile not found for simplicity but we should
        friend.setProfileIdRef(profileIdRef);
        return friendDao.save(friend);
    }

    @Override
    public Friend deleteFriend(Friend friend) {
        friendDao.delete(friend);
        return friend;
    }

    @Override
    public Friend changePhoneNumber(int id, long phone) throws ProfileNotFoundException {
        Friend changedFriend = friendDao.findById(id).orElse(null);
        if (changedFriend == null) {
            throw new ProfileNotFoundException("Friend with id " + id + " not found.");
        }
        changedFriend.setPhone(phone);
        friendDao.save(changedFriend);
        return changedFriend;
    }
}
