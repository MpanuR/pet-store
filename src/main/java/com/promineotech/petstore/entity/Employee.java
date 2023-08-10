package com.promineotech.petstore.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    private long employeeId;
    // One-to-many relationship with PetStore
    // key column is pet_store_id
    @ManyToOne(cascade = CascadeType.ALL) // Many employees can work at one pet store
    @JoinColumn(name = "pet_store_id") // name = "pet_store_id" is the name of the column in the database
    private PetStore petStore;
    
    private String employeeFirstName;
    private String employeeLastName;
    private String employeePhone;
    private String employeeJobTitle;

}
