package com.example.service;

import com.example.dao.mapper.CustomerMapper;
import com.example.dao.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper mapper;

    @Override
    public CustomerEntity getCustomerById(Integer id) {
        return mapper.findById(id);
    }

    @Override
    public List<CustomerEntity> getCustomerList(){
        return mapper.queryList();
    };

}