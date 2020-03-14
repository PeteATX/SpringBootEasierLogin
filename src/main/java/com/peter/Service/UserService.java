package com.peter.Service;

import com.peter.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}