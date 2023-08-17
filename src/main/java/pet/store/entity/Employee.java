package pet.store.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
// This code creates the Employee class, which is a table in the database.
// It also defines the class attributes of the Employee class,
// which are the columns in the database table. The @Id and @ManyToOne
// annotations tell Hibernate that the employeeId column is the primary key
// for the Employee table and the pet_store_id column is a foreign key to
// the PetStore table. The @JoinColumn annotation tells Hibernate the name
// of the column in the database that the petStore attribute corresponds to.

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String employeeFirstName;
    private String employeeLastName;
    private String employeePhone;
    private String employeeJobTitle;

    @ManyToOne(cascade = CascadeType.ALL) // Many employees can work at one pet store
    @JoinColumn(name = "pet_store_id") // name = "pet_store_id" is the name of the column in the database
    private PetStore petStore;

}
