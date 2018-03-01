package com.example.service;

import com.example.dao.entity.UserEntity;
import com.example.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
    public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public UserEntity getUserByUserName(String username) {
        return mapper.findByUserName(username);
    }

    @Override
    public List<UserEntity> getUserList(String username) {
        return mapper.queryList(username);
    }

    @Override
    public int add(UserEntity user) {
        return mapper.insertEntity(user);
    }

    @Override
    public int update(UserEntity user) { return  mapper.updateEntity(user); }

    @Override
    public int delete(Integer id) {
        return mapper.deleteEntity(id);
    }


}