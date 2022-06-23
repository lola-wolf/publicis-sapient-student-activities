package com.example.springbootdbapp.model.service;

import com.example.springbootdbapp.exceptions.ProfileNotFoundException;
import com.example.springbootdbapp.model.beans.Friend;

public interface FriendService {
    public Friend addFriend(int profileIdRef, Friend friend);

    public Friend deleteFriend(Friend friend);

    public Friend changePhoneNumber(int id, long phone) throws ProfileNotFoundException;
}
