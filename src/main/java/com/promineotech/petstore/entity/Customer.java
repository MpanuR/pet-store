package com.promineotech.petstore.entity;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Customer {

    // Add the @Id annotation to identify the primary key
    @Id
    // Add the @GeneratedValue annotation to specify that the ID is generated
    @GeneratedValue
    // Add a private field of type long to store the customer ID
    private long customerId;
    // Add a private field of type String to store the customer's first name
    private String customerFirstName;
    // Add a private field of type String to store the customer's last name
    private String customerLastName;
    // Add a private field of type String to store the customer's email address
    private String customerEmail;
    // Many-to-many relationship with petStore
    // Customers can shop at multiple pet stores
    // Pet stores can have multiple customers
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
    private Set<PetStore> petStores;
}
