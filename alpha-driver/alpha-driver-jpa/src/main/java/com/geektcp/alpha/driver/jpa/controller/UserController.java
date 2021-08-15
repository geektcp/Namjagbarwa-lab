package com.geektcp.alpha.driver.jpa.controller;

import com.geektcp.alpha.driver.jpa.domain.User;
import com.geektcp.alpha.driver.jpa.service.IUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private IUserService userService;

    @GetMapping(value = "/add/{id}/{name}/{address}")
    public User addUser(@PathVariable int id, @PathVariable String name,
                        @PathVariable String address) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        userService.saveUser(user);
        return user;
    }

    @GetMapping(value = "/delete/{id}")
    public void deleteBook(@PathVariable int id) {
        userService.delete(id);
    }

    @GetMapping(value = "/")
    public List<User> getBooks() {
        return userService.findAll();
    }

    @GetMapping(value = "/test")
    public List<User> findByName() {
        return userService.findByName("内置用户");
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable int id) {
        return userService.findOne(id);
    }

    @GetMapping(value = "/search/name/{name}")
    public List<User> getBookByName(@PathVariable String name) {
        return userService.findByName(name);
    }

}
