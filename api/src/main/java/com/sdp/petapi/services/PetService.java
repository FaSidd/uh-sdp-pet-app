package com.sdp.petapi.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdp.petapi.dao.PetDao;
import com.sdp.petapi.models.Message;
import com.sdp.petapi.models.Pet;

@Service
public class PetService {
	
	@Autowired
	private PetDao petDao;
	
	public Collection<Pet> getPets() {
		return petDao.getPets();
	}


	public Pet createPet(Pet pet) {
		return petDao.createPet(pet);
	}

	public Message deletePet(String id) {
		return petDao.deletePet(id);
	}

	public Message putPet(Pet pet) {
		return petDao.putPet(pet);
	}
}
