package com.telusko.springweb.controller;

import com.telusko.springweb.Entity.User;
import com.telusko.springweb.Service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/Users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/UsersList")
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @PostMapping("/addingUser")
    public ResponseEntity<?> addingUser(@RequestBody @NotNull User user) {
        User check_user_record = userService.findByUserName(user.getName()).orElse(null);
        if (check_user_record==null) {
            userService.saveUsers(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user.toString());
        }
        return ResponseEntity.ok("Record already present! Please create a new one");
    }

    @PutMapping("/updateUser/{mobno}")
    public ResponseEntity<?> updatingUserNumber(@RequestBody User user,@PathVariable String mobno){
        User userInDb=userService.findByUserName(user.getName()).orElse(null);
        if(userInDb==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else if (!userInDb.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userInDb.setMobno(mobno);
        userService.saveUsers(userInDb);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
