package com.example.ab.myapplication;

public class User {
    String id;
    String username;
    User currentUser;
    private User() {
    }

    public User getCurrentUser() {
        if (currentUser == null) {
            System.out.println("User is not yet Logged In");
            return null;
        }
        return currentUser;
    }

    public User CreateUser(String id, String username) {
        if (currentUser != null) {
            System.out.println("User already Logged In");
            return null;
        }
        currentUser = new User();
        currentUser.id = id;
        currentUser.username = username;
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
