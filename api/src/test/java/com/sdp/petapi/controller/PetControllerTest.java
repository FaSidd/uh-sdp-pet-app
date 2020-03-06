package com.sdp.petapi.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdp.petapi.controllers.PetController;
import com.sdp.petapi.models.Pet;
import com.sdp.petapi.services.PetService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PetControllerTest {

  Pet pet;

  @Mock
  PetService petService;

  // makes a pet controller whose petService is the mock above
  @InjectMocks
  PetController petController;

  @BeforeEach
  public void init() throws Exception {
    ObjectMapper om = new ObjectMapper();
    pet = om.readValue(new File("src/test/java/com/sdp/petapi/resources/mocks/petObject.json"), Pet.class);
  }

  @AfterEach
  public void cleanup() {
  }

  @Test
  public void get_all_pets() {
    // Since the petService is a mock it will return null on method calls, so
    // we must specify what it will return given a specific method call
    when(petService.getEmployeeAllPets()).thenReturn(Collections.singletonList(pet));
    List<Pet> list = petController.getAllPets();
    assertEquals(Collections.singletonList(pet), list);
  }

  @Test
  public void get_pet_by_id_exists() {
    when(petService.getEmployeePetById(pet.getId())).thenReturn(pet);
    Pet returnPet = petController.getPetById(pet.getId());
    assertEquals(pet, returnPet);
  }

  @Test
  public void get_pet_by_id_nonexistent() {
    String id = pet.getId();
    pet.setId(id + "999");

    when(petService.getEmployeePetById(pet.getId())).thenReturn(null);
    Pet updatePet = petController.getPetById(pet.getId());
    assertNull(updatePet);
  }

  @Test
  public void get_pet_by_id_null() {
    when(petService.getEmployeePetById(null)).thenReturn(null);
    Pet returnPet = petController.getPetById(null);
    assertNull(returnPet);
  }

  @Test
  public void create_pet_good() {
    when(petService.createPet(pet)).thenReturn(pet);
    Pet returnPet = petController.createPet(pet);
    assertEquals(pet, returnPet);
  }

  @Test
  public void create_pet_null() {
    when(petService.createPet(null)).thenReturn(null);
    Pet returnPet = petController.createPet(null);
    assertNull(returnPet);
  }
  
  @Test
  public void put_pet_good() {
    when(petService.putPet(pet)).thenReturn(pet);
    Pet returnPet = petController.putPet(pet.getId(), pet);
    assertEquals(pet, returnPet);
  }

  @Test
  public void put_pet_returns_null() {
    when(petService.putPet(pet)).thenReturn(null);
    Pet returnPet = petController.putPet(pet.getId(), pet);
    assertNull(returnPet);
  }
}