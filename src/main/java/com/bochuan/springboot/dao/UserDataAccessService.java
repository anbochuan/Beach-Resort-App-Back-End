package com.bochuan.springboot.dao;


import com.bochuan.springboot.modal.User;
import com.bochuan.springboot.security.ApplicationUserPermission;
import com.bochuan.springboot.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Data Access layer
@Repository("UserDetailsDao")
public class UserDataAccessService implements UserDao {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataAccessService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public User selectUserByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, User.class, "users");
    }

    @Override
    public UUID insertUser(UUID uuid, User user) {
        User newUser = new User(uuid,
                                user.getUsername(),
                                passwordEncoder.encode(user.getPassword()),
                                ApplicationUserRole.FARMER.getGrantedAuthorities(),
                                user.isAccountNonExpired(),
                                user.isAccountNonLocked(),
                                user.isCredentialsNonExpired(),
                                user.isEnabled());

        mongoTemplate.insert(newUser,"users");
        return uuid;
    }
//
//    @Override
//    public User selectUserById(UUID uuid) {
//        Query query = new Query(Criteria.where("uuid").is(uuid));
//        return mongoTemplate.findOne(query, User.class, "users");
//    }
//
//    @Override
//    public User selectUserByNameAndPwd(String username, String password) {
//        Query query = new Query(Criteria.where("username").is(username)
//                                        .andOperator(Criteria.where("password").is(password)));
//        return mongoTemplate.findOne(query, User.class, "users");
//    }
//
//    @Override
//    public List<User> selectAllUser() {
//        return mongoTemplate.findAll(User.class, "users");
//    }
//
//    @Override
//    public User deleteUserById(UUID uuid) {
//        Query query = new Query(Criteria.where("uuid").is(uuid));
//        return mongoTemplate.findAndRemove(query, User.class, "users");
//    }
//
//    @Override
//    public User updateUserById(UUID uuid, User user) {
//        Query query = new Query(Criteria.where("uuid").is(uuid));
//        Update update = new Update();
//        update.set("username", user.getUsername())
//                .set("password", user.getPassword());
//        return mongoTemplate.findAndModify(query, update, User.class, "users");
//    }
}
