package com.damo.bootmall.dao;

import com.damo.bootmall.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String name);
}
