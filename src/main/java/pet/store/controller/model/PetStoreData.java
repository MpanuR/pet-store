package pet.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetStoreData {

    private Long petStoreId;
    private String petStoreName;
    private String petStoreAddress;
    private String petStoreCity;
    private String petStoreState;
    private String petStoreZip;
    private String petStorePhone;

    Set<PetStoreEmployee> employees = new HashSet<>();
    Set<PetStoreCustomer> customers = new HashSet<>();

    // add constructor that takes a PetStore as a parameter
    // Create a new PetStoreData object to copy the data from the
    // PetStore object to the PetStoreData object
    public PetStoreData(PetStore petStore) {

        // Copy the data from the PetStore object to the PetStoreData object
        petStoreId = petStore.getPetStoreId();
        petStoreName = petStore.getPetStoreName();
        petStoreAddress = petStore.getPetStoreAddress();
        petStoreCity = petStore.getPetStoreCity();
        petStoreState = petStore.getPetStoreState();
        petStoreZip = petStore.getPetStoreZip();
        petStorePhone = petStore.getPetStorePhone();

        // Initialize the collection of customers and employees

        for (Customer customer : petStore.getCustomers()) {
            customers.add(new PetStoreCustomer(customer));
        }

        for (Employee employee : petStore.getEmployees()) {
            employees.add(new PetStoreEmployee(employee));
        }

    }

    @Data
    @NoArgsConstructor
    public static class PetStoreCustomer {
        private Long customerId;
        private String customerFirstName;
        private String customerLastName;
        private String customerEmail;

        public PetStoreCustomer(Customer customer) {
            customerId = customer.getCustomerId();
            customerFirstName = customer.getCustomerFirstName();
            customerLastName = customer.getCustomerLastName();
            customerEmail = customer.getCustomerEmail();

        }

    }

    @Data
    @NoArgsConstructor
    public static class PetStoreEmployee {
        private Long employeeId;
        private String employeeFirstName;
        private String employeeLastName;
        private String employeePhone;
        private String employeeJobTitle;

        public PetStoreEmployee(Employee employee) {
            employeeId = employee.getEmployeeId();
            employeeFirstName = employee.getEmployeeFirstName();
            employeeLastName = employee.getEmployeeLastName();
            employeePhone = employee.getEmployeePhone();
            employeeJobTitle = employee.getEmployeeJobTitle();

        }
    }

}
