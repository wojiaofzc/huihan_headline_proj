package com.example.service;

import com.example.dao.entity.CustomerEntity;
import java.util.List;

public interface CustomerService {

    CustomerEntity getCustomerById(Integer id);

    public List<CustomerEntity> getCustomerList();

}
