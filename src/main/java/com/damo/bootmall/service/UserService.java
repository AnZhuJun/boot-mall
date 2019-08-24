package com.damo.bootmall.service;

import com.damo.bootmall.bean.User;

public interface UserService {
    void save(User user);

    User findByUsername(String name);


}
