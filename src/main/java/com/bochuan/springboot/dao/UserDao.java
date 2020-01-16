package com.bochuan.springboot.dao;
import com.bochuan.springboot.modal.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

     User selectUserByUsername(String username);

     UUID insertUser(UUID uuid, User user);
//
//    User selectUserById(UUID uuid);
//
//    User selectUserByNameAndPwd(String username, String password);
//
//    List<User> selectAllUser();
//
//    User deleteUserById(UUID uuid);
//
//    User updateUserById(UUID id, User user);
}
