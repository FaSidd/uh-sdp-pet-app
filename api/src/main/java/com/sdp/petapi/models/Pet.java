package com.sdp.petapi.models;

import java.util.*;
import java.util.stream.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
public @Data class Pet {
  @Id
  private String id;

  private String name;
  private String type;
  private String sex; // M, F
  private String age; // newborn, young, adult
  private String size; // small, medium, large, extra large
  private Double weight;
  private Date dateAdded;
  private String description;
  private String[] imageNames; // link to photos
  private boolean isAdopted;
  private boolean isActive;
  
  public Pet(String name, String type, String sex, String age, String size, double weight, String description,
    List<String> images) {
      this.name = name;
      this.type = type;
      this.sex = sex;
      this.age = age;
      this.size = size;
      this.weight = weight;
      this.description = description;
      images.stream().collect(Collectors.toSet()).toArray(this.imageNames);
      this.dateAdded = new Date();
      this.isActive = true;
  }
  
  public Pet(String name, String type, String sex, String age, String size, double weight, String description,
    String[] images) {
      this.name = name;
      this.type = type;
      this.sex = sex;
      this.age = age;
      this.size = size;
      this.weight = weight;
      this.description = description;
      Arrays.asList(images).stream().collect(Collectors.toSet()).toArray(this.imageNames);
      this.dateAdded = new Date();
      this.isActive = true;
  }
  
  public Pet(String id, String name, String type, String sex, String age, String size, Double weight, Date date,
    String desc, List<String> images, boolean adopt, boolean status){
      this.id = id;
      this.name = name;
      this.type = type;
      this.sex = sex;
      this.age = age;
      this.size = size;
      this.weight = weight;
      this.dateAdded = date;
      this.description = desc;
      images.stream().collect(Collectors.toSet()).toArray(this.imageNames);
      this.isAdopted = adopt;
      this.isActive = status;
  }

}