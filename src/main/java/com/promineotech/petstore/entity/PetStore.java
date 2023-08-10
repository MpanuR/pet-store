package com.promineotech.petstore.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.Set;

@Entity
@Data
public class PetStore {

    @Id
    @GeneratedValue
    private long petStoreId;

    private String petStoreName;
    private String petStoreAddress;
    private String petStoreCity;
    private String petStoreState;
    private String petStoreZip;
    private String petStorePhone;

    // One-to-many relationship with Employee
    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
    // mappedBy = "petStore" is the name of the field in the Employee class
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Employee> employees;

    // Many-to-many relationship with Customer
    @ManyToMany(cascade = CascadeType.PERSIST)
    // @JoinTable annotation is used to specify the join table
    // name = "pet_store_customers" is the name of the join table
    // joinColumns = @JoinColumn(name = "pet_store_id") is the name of the column in
    // the join table that references the pet store
    // inverseJoinColumns = @JoinColumn(name = "customer_id") is the name of the
    // column in the join table that references the customer
    // The joinColumns and inverseJoinColumns are the foreign keys in the join table
    @JoinTable(name = "pet_store_customers", joinColumns = @JoinColumn(name = "pet_store_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Customer> customers;

    // public PetStore() {

    // }

}
