package com.bochuan.springboot.api;

import com.bochuan.springboot.modal.User;
import com.bochuan.springboot.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


// API layer / Controller layer
@RequestMapping("api/v1")
@RestController
public class UserController {

    private UserService userService;
    // There is no default instance of BCryptPasswordEncoder that can be injected in the UserController class
    // need to add Bean class into SpringbootApplication.java
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
//    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userService = userService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    @PostMapping
    public UUID signUp(@Valid @NotNull @RequestBody User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userService.addUser(user);
    }

    @RequestMapping("/login")
    @PostMapping
    public void logIn(@Valid @NotNull @RequestBody User user) {
////        String encodedPwd = bCryptPasswordEncoder.encode(password);
//        if(userService.getUserByNameAndPwd(user.getUsername(), user.getPassword()) != null) {
////            HashMap<String, String> result = new HashMap<>();
//
//            String token = Jwts.builder().setSubject(user.getUsername())
//                    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
//                    .signWith(SignatureAlgorithm.HS256, "myJwtSecret")
//                    .compact();
//            return token;
//        } else {
//            return "invalid username or password";
//        }
    }

//    @RequestMapping("/user")
//    @GetMapping()
//    public User getUserById(@RequestParam(value = "id") UUID uuid) {
//        return userService.getUserById(uuid);
//    }
//
//    @RequestMapping("/users")
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @DeleteMapping()
//    public User deleteUserById(@RequestParam(value = "id") UUID uuid) {
//        return userService.deleteUserById(uuid);
//    }
//
//    @PutMapping()
//    public User updateUserById(@RequestParam(value = "id") UUID uuid, @Valid @NotNull @RequestBody User user) {
//        return userService.updateUserById(uuid, user);
//    }
}
