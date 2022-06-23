package com.example.springbootdbapp.exceptions;

@SuppressWarnings("serial")
public class ProfileNotFoundException extends Exception {

    public ProfileNotFoundException() {
        super();
    }

    public ProfileNotFoundException(String e) {
        super(e);
    }
}
