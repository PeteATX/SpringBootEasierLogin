package com.peter.Service;
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}

