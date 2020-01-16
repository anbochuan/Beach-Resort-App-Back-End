package com.bochuan.springboot.service;

import com.bochuan.springboot.dao.UserDao;
import com.bochuan.springboot.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Service layer / Business logic layer
@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("UserDetailsDao") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }

    public UUID addUser(User user) {
        UUID uuid = UUID.randomUUID();
        return userDao.insertUser(uuid, user);
    }
//
//    public User getUserById(UUID uuid) {
//        return userDao.selectUserById(uuid);
//    }
//
//    public User getUserByNameAndPwd(String username, String password) {
//        return userDao.selectUserByNameAndPwd(username, password);
//    }
//
//    public List<User> getAllUsers() {
//        return userDao.selectAllUser();
//    }
//
//    public User deleteUserById(UUID uuid) {
//        return userDao.deleteUserById(uuid);
//    }
//
//    public User updateUserById(UUID uuid, User user) {
//        return userDao.updateUserById(uuid, user);
//    }
}
