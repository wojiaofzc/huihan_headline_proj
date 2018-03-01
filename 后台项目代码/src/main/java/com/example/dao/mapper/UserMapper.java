package com.example.dao.mapper;

import com.example.dao.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM USER WHERE username LIKE CONCAT('%',#{username},'%')")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property =  "username", column = "username"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mail", column = "mail"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "create_date", column = "create_date"),
            @Result(property = "modify_date", column = "modify_date")})
     List<UserEntity> queryList(String username);

    @Select("SELECT * FROM USER WHERE username = #{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property =  "username", column = "username"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "mail", column = "mail"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "create_date", column = "create_date"),
            @Result(property = "modify_date", column = "modify_date")})
    UserEntity findByUserName(String username);

    @Insert("insert into USER(id,name,username,password,phone,mail,create_date,modify_date) values(#{id},#{name},#{username},#{password},#{phone},#{mail},#{create_date},#{modify_date})")
    int insertEntity(UserEntity user);

    @Update("UPDATE USER SET name=#{name},password=#{password},phone=#{phone},mail=#{mail},modify_date=#{modify_date} WHERE id=#{id}")
    int updateEntity(UserEntity user);

    @Delete("DELETE FROM USER WHERE id =#{id}")
    int deleteEntity(Integer id);
}
