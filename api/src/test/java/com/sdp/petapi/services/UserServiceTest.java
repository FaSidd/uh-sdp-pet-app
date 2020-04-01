package com.sdp.petapi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdp.petapi.dao.UserDao;
import com.sdp.petapi.models.Pet;
import com.sdp.petapi.models.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
  Pet pet;
  User employee, webUser;

  @Mock
  UserDao userDao;

  // makes a userService whose userDao is the mock above
  @InjectMocks
  UserService userService;

  @BeforeEach
  public void init() throws Exception {
    ObjectMapper om = new ObjectMapper();
    pet = om.readValue(new File("src/test/java/com/sdp/petapi/resources/mocks/petObject.json"), Pet.class);
    employee = om.readValue(new File("src/test/java/com/sdp/petapi/resources/mocks/employeeObject.json"), User.class);
    webUser = om.readValue(new File("src/test/java/com/sdp/petapi/resources/mocks/webUserObject.json"), User.class);
  }

  @AfterEach
  public void cleanup() {
  }

  @Test
  public void get_all_users() {
    // Since the userDao is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(userDao.getAllUsers()).thenReturn(Arrays.asList(new User[] {employee, webUser}));
    List<User> list = userService.getAllUsers();
    assertEquals(Arrays.asList(new User[] {employee, webUser}), list);
  }
  @Test
  public void get_user_by_id() {
    String id = "001";

    // Since the userDao is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(userDao.getUserById(id)).thenReturn(employee);
    User actual_user = userService.getUserById(id);
    assertEquals(employee, actual_user);
  }

  @Test
  public void create_user() {
    // Since the userDao is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(userDao.createUser(webUser)).thenReturn(webUser);
    User returnUser = userService.createUser(webUser);
    assertEquals(webUser, returnUser);
  }

  @Test
  public void put_user() {
    // Since the userDao is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(userDao.putUser(webUser)).thenReturn(webUser);
    User returnedUser = userService.putUser(webUser);
    assertEquals(webUser, returnedUser);
  }

  @Test
  public void delete_user() {
    // Since the userDao is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(userDao.deleteUser("002")).thenReturn(webUser);
    User returnedUser = userService.deleteUser("002");
    assertEquals(webUser, returnedUser);
  }

  @Test
  public void add_pet_by_id_to_user_favorites_list() {
    // Since the userDao is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(userDao.addPetToFavorites(webUser, "001")).thenReturn(true);
    Boolean result = userService.addPetToFavorites(webUser, "001");
    assertTrue(result);
  }

  @Test
  public void remove_pet_by_id_from_user_favorites_list() {
    // Since the userDao is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(userDao.removePetFromFavorites(webUser, "001")).thenReturn(true);
    Boolean result = userService.removePetFromFavorites(webUser, "001");
    assertTrue(result);
  }

  @Test
  public void add_pet_by_id_to_user_recently_visited_list() {
    // Since the userDao is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(userDao.addPetToRecents(webUser, "001")).thenReturn(true);
    Boolean result = userService.addPetToRecents(webUser, "001");
    assertTrue(result);
  }

  @Test
  public void get_user_favorites_list() {
    // Since the userDao is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(userDao.getFavoritePets("002")).thenReturn(Collections.singletonList(pet));
    List<Pet> list = userService.getFavoritePets("002");
    assertEquals(list, Collections.singletonList(pet));
  }

  @Test
  public void get_user_recently_visited_list() {
    // Since the userDao is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(userDao.getRecentPets("002")).thenReturn(Collections.singletonList(pet));
    List<Pet> list = userService.getRecentPets("002");
    assertEquals(list, Collections.singletonList(pet));
  }

  @Test
  public void user_exists_by_email() {
    // Since the userDao is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(userDao.existsByEmail("1234@mail.com")).thenReturn(true);
    Boolean resp = userService.existsByEmail("1234@mail.com");
    assertTrue(resp);
  }
}