package pet.store.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
    @Autowired
    private PetStoreService petStoreservice;

    @PostMapping("/pet_store")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreData createPetStore(
            @RequestBody PetStoreData petStoreData) {
        log.info("created a request to create a new pet store: {}", petStoreData);
        return petStoreservice.savePetStore(petStoreData);

    }

    // create a public method to update the pet store data
    @PutMapping("/pet_store/{storeId}")
    public PetStoreData updatePetStore(
            @PathVariable Long storeId,
            @RequestBody PetStoreData petStoreData) {
        petStoreData.setPetStoreId(storeId);
        log.info("received a request to update a pet store: {}", petStoreData);
        return petStoreservice.savePetStore(petStoreData);

    }

}
