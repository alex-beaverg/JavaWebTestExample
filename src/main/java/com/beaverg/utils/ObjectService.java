package com.beaverg.utils;

import com.beaverg.domain.User;

public class ObjectService {

    public static User createValidUser() {
        return new User(PropertyGetter.getData("valid_email"), PropertyGetter.getData("valid_pass"));
    }

    public static User createInvalidUser() {
        return new User(PropertyGetter.getData("invalid_email"), PropertyGetter.getData("invalid_pass"));
    }
}
