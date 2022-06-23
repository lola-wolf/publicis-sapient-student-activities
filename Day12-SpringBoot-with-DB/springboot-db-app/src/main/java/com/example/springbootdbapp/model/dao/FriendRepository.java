package com.example.springbootdbapp.model.dao;

import com.example.springbootdbapp.model.beans.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Integer> {

    @Query("select f from Friend f where profileIdRef = ?1")
    public List<Friend> getFriendsFromProfile(int id);
}
