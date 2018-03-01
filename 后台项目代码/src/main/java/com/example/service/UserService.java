package com.example.service;

import com.example.dao.entity.UserEntity;
import java.util.List;

public interface UserService {

    UserEntity getUserByUserName(String username);

    public List<UserEntity> getUserList(String username);

    public int add(UserEntity user);

    public int update(UserEntity user);

    public int delete(Integer id);
}
