package com.example.springbootdbapp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbootdbapp.model.beans.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
