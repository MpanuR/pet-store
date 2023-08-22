package pet.store.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
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

    @PostMapping("/pet_store/{petStoreId}/employee")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreEmployee addEmployeeToPetStore(
            @PathVariable Long petStoreId,
            @RequestBody PetStoreEmployee petStoreEmployee) {

        log.info("received a request to add an employee to a pet store: {}", petStoreEmployee, petStoreId);

        PetStoreEmployee addedEmployee = petStoreservice.saveEmployee(petStoreId, petStoreEmployee);

        return addedEmployee;

    }

    @PostMapping("/pet_store/{petStoreId}/customer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreCustomer addCustomerToPetStore(
            @PathVariable Long petStoreId,
            @RequestBody PetStoreCustomer petStoreCustomer) {

        log.info("received a request to add a customer to a pet store: {}", petStoreCustomer, petStoreId);

        PetStoreCustomer addedCustomer = petStoreservice.saveCustomer(petStoreId, petStoreCustomer);

        return addedCustomer;

    }

    @GetMapping("/pet_store")
    public List<PetStoreData> listAllPetStores() {

        return petStoreservice.retrieveAllPetStores();

    }

    @GetMapping("/pet_store/{petStoreId}")
    public PetStoreData listPetStoreById(
            @PathVariable Long petStoreId) {
        return petStoreservice.retrievePetStoreById(petStoreId);
    }

    @DeleteMapping("/pet_store/{petStoreId}")
    public Map<String, String> deletePetStoreById(
            @PathVariable Long petStoreId) {
        petStoreservice.deletePetStoreById(petStoreId);

        return Map.of("message", "Deletion of pet store with id " + petStoreId + " was successful");

    }
}
