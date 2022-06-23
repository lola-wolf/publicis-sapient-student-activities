package com.example.springbootdbapp.controller;

import com.example.springbootdbapp.exceptions.ProfileNotFoundException;
import com.example.springbootdbapp.model.beans.Friend;
import com.example.springbootdbapp.model.beans.Profile;
import com.example.springbootdbapp.model.service.FriendService;
import com.example.springbootdbapp.model.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private FriendService friendService;

    @PostMapping
    public ResponseEntity<Object> store(@RequestBody Profile profile) {
        Profile storedProfile = profileService.storeProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(storedProfile);
    }

    @GetMapping
    public ResponseEntity<Object> getProfiles() {
        List<Profile> list = profileService.fetchProfiles();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProfile(@PathVariable("id") int id) throws ProfileNotFoundException {
        try {
            Profile profile = profileService.fetchProfile(id);
            return ResponseEntity.status(HttpStatus.OK).body(profile);
        } catch (ProfileNotFoundException e) {
            Map<String, String> map = new HashMap<>();
            map.put("error", e.getMessage());
            map.put("status", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @PostMapping("/{profileId}")
    public ResponseEntity<Object> addFriend(@RequestBody Friend friend,
                                            @PathVariable("profileId") int id) {
        Friend createdFriend = friendService.addFriend(id, friend);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Added " + createdFriend.getName());
        map.put("profile", "Profile id: " + createdFriend.getProfileIdRef());
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PutMapping("/{profileId}/{phone}")
    public ResponseEntity<Object> modifyPhone(@PathVariable("profileId") int id,
                                              @PathVariable("phone") long phone) throws ProfileNotFoundException {
        try {
            Profile changedProfile = profileService.changePhone(id, phone);
            return ResponseEntity.status(HttpStatus.OK).body(changedProfile);
        } catch (ProfileNotFoundException e) {
            Map<String, String> map = new HashMap<>();
            map.put("error", e.getMessage());
            map.put("status", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @DeleteMapping("/{profileId}/{friendId}")
    public ResponseEntity<Object> deleteFriend(@PathVariable("profileId") int id,
                                               @PathVariable("friendId") int friendId) throws ProfileNotFoundException {
        try {
            Profile changedProfile = profileService.deleteFriend(id, friendId);
            return ResponseEntity.status(HttpStatus.OK).body(changedProfile);
        } catch (ProfileNotFoundException e) {
            Map<String, String> map = new HashMap<>();
            map.put("error", e.getMessage());
            map.put("status", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @PutMapping("/{profileId}/{friendId}/{phone}")
    public ResponseEntity<Object> changeFriendPhone(@PathVariable("profileId") int id,
                                                    @PathVariable("friendId") int friendId,
                                                    @PathVariable("phone") long phone) throws ProfileNotFoundException {
        try {
            Friend friend = friendService.changePhoneNumber(friendId, phone);
            return ResponseEntity.status(HttpStatus.OK).body(friend);
        } catch (ProfileNotFoundException e) {
            Map<String, String> map = new HashMap<>();
            map.put("error", e.getMessage());
            map.put("status", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }
}