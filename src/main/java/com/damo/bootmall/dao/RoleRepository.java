package com.damo.bootmall.dao;

import com.damo.bootmall.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends JpaRepository<Role,Long>{
    
}
