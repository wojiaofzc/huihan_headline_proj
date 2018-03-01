package com.example.dao.mapper;

import com.example.dao.entity.CustomerEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerMapper {
    @Select("SELECT * FROM USER")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mail", column = "mail"),
            @Result(property = "name", column = "name"),
            @Result(property = "deptId", column = "deptId")})
    List<CustomerEntity> queryList();

    @Select("SELECT * FROM USER name = #{name}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mail", column = "mail"),
            @Result(property = "name", column = "name"),
            @Result(property = "deptId", column = "deptId")})
    CustomerEntity findById(Integer id);

}
