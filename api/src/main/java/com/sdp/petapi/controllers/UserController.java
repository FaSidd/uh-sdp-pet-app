package com.sdp.petapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdp.petapi.models.Pet;
import com.sdp.petapi.models.User;

import com.sdp.petapi.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getAllUser() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable String id) {
    return userService.getUserById(id);
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PutMapping("/{id}")
  public User putUser(@PathVariable String id, @RequestBody User user) {
    return (id == null || user == null || !id.equals(user.getId())) ? null : userService.putUser(user);
  }

  @DeleteMapping("/{id}")
  public User deleteUser(@PathVariable String id) {
    return userService.deleteUser(id);
  }

  @PostMapping("/fav/{id}")
  public Boolean addPetToFavorites(@PathVariable String id, @RequestBody User user) {
    return userService.addPetToFavorites(user, id);
  }

  @PutMapping("/fav/{id}")
  public Boolean removePetFromFavorites(@PathVariable String id, @RequestBody User user) {
    return userService.removePetFromFavorites(user, id);
  }

  @PostMapping("/recent/{id}")
  public Boolean addPetToRecents(@PathVariable String id, @RequestBody User user) {
    return userService.addPetToRecents(user, id);
  }

  @GetMapping("/fav/{id}")
  public List<Pet> getFavoritePets(@PathVariable String id) {
    return userService.getFavoritePets(id);
  }

  @PutMapping("/recents/{id}")
  public List<Pet> getRecentPets(@PathVariable String id) {
    return userService.getRecentPets(id);
  }
  
}
