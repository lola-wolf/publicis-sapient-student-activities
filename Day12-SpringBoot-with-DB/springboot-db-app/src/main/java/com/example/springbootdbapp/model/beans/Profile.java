package com.example.springbootdbapp.model.beans;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;
    private String name;
    private long phone;
    private LocalDate dob;

    public List<Friend> getFriends() {
        return Friends;
    }

    public void setFriends(List<Friend> friends) {
        Friends = friends;
    }

    // a profile can have one or more friends, use One To Many
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "profileidref")
    private List<Friend> Friends;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
