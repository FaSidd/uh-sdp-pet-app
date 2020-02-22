package com.sdp.petapi;

import com.sdp.petapi.models.Pet;
import com.sdp.petapi.repositories.PetRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Mono;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class PetDocumentTest {

  private PetRepository petRepository;

  @Test
  public void persist() throws Exception {

    Pet pe = new Pet();
    Mono<Pet> save = this.petRepository.save(pe);
    // TODO: IS THERE A BETTER WAY TO DO THIS???
    // StepVerifier.create(save).expectNextMatches(p -> p.getId() != null &&
    // p.getName().equalsIgnoreCase("Buddy")) .verifyComplete();
  }

}