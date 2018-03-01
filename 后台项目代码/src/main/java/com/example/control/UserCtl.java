package com.example.control;

import com.example.dao.entity.ReqUser;
import com.example.dao.entity.RespCode;
import com.example.dao.entity.RespEntity;
import com.example.dao.entity.UserEntity;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by fzc on 2018-2-1
 */

@RestController
@RequestMapping("/api/users")
public class UserCtl {

    static Logger log = Logger.getLogger(UserCtl.class.getName());

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RespEntity userList(@RequestBody ReqUser reqUser) {
        List<UserEntity> userList;
        try{
            userList = userService. getUserList(reqUser.getUsername());
            return new RespEntity(RespCode.SUCCESS,userList.toArray());
        } catch (Exception e){
            return new RespEntity(RespCode.FAIL,"");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity login(@RequestBody ReqUser reqUser) {
        if (checkLogin(reqUser))
            return new RespEntity(RespCode.SUCCESS,reqUser);
        else return new RespEntity(RespCode.FAIL,reqUser);
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity newUser(@RequestBody UserEntity user){
        if (userService.add(user) ==  1) {
            return new RespEntity(RespCode.SUCCESS, user);
        } else return new RespEntity(RespCode.FAIL,user);
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity searchUsers(@RequestBody ReqUser reqUser) {
        UserEntity user = userService.getUserByUserName(reqUser.getUsername());
        return new RespEntity(RespCode.SUCCESS,user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity updateUser(@RequestBody UserEntity user){
        UserEntity userlast = userService.getUserByUserName(user.getUsername());
        user.setId(userlast.getId());
        if (userService.update(user) ==  1) {
            return new RespEntity(RespCode.SUCCESS, user);
        } else return new RespEntity(RespCode.FAIL,user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity deleteUser(@RequestBody UserEntity user){
        UserEntity userDelete = userService.getUserByUserName(user.getUsername());
        if (userService.delete(userDelete.getId()) ==  1) {
            return new RespEntity(RespCode.SUCCESS, userDelete);
        } else return new RespEntity(RespCode.FAIL,userDelete);
    }

    private Boolean checkLogin(ReqUser reqUser){
        try {
            UserEntity user = userService.getUserByUserName(reqUser.getUsername());
            return user.getPassword().equals(reqUser.getPwd());
        }catch (Exception e){
            throw e;
        }
    }
}
