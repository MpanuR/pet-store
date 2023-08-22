package pet.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;

@Service
public class PetStoreService {

    @Autowired
    private PetStoreDao petStoreDao;

    @Transactional(readOnly = false)
    public PetStoreData savePetStore(PetStoreData petStoreData) {
        Long petStoreId = petStoreData.getPetStoreId();
        PetStore petStore = findOrCreatePetStore(petStoreId);
        copyPetStoreFields(petStoreData, petStore);

        return new PetStoreData(petStoreDao.save(petStore));
    }

    private PetStore findOrCreatePetStore(Long petStoreId) {

        /*
         * if (petStoreId == null) {
         * return new PetStore();
         * } else {
         * return findPetStoreById(petStoreId);
         * }
         */

        PetStore petStore;

        if (Objects.isNull(petStoreId)) {
            petStore = new PetStore();
        } else {
            petStore = findPetStoreById(petStoreId);
        }
        return petStore;

    }

    private PetStore findPetStoreById(Long petStoreId) {
        return petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException(
                "PetStoreId not found " + petStoreId));
    }

    private void copyPetStoreFields(PetStoreData petStoreData, PetStore petStore) {
        petStore.setPetStoreId(petStoreData.getPetStoreId());
        petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
        petStore.setPetStorePhone(petStoreData.getPetStorePhone());

    }

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional(readOnly = false)
    public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
        PetStore petStore = findPetStoreById(petStoreId);
        Long employeeId = petStoreEmployee.getEmployeeId();
        Employee employee = findOrCreateEmployee(petStoreId, employeeId);

        copyEmployeeFields(employee, petStoreEmployee);

        employee.setPetStore(petStore);
        petStore.getEmployees().add(employee);

        return new PetStoreEmployee(employeeDao.save(employee));
    }

    private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
        employee.setEmployeeId(petStoreEmployee.getEmployeeId());
        employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
        employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
        employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
    }

    private Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
        if (Objects.isNull(employeeId)) {
            return new Employee();
        } else {
            return findEmployeeById(petStoreId, employeeId);
        }
    }

    private Employee findEmployeeById(Long petStoreId, Long employeeId) {

        Employee employee = employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException(
                "EmployeeId not found " + employeeId));

        if (employee.getPetStore().getPetStoreId() != petStoreId) {
            throw new NoSuchElementException(
                    "The employee Id " + employeeId + "is not employed by this pet store" + petStoreId);
        }

        return employee;
    }

    @Autowired
    private CustomerDao customerDao;

    @Transactional(readOnly = false)
    public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
        PetStore petStore = findPetStoreById(petStoreId);
        Long customerId = petStoreCustomer.getCustomerId();
        Customer customer = findOrCreateCustomer(customerId, petStoreId);
        copyCustomerFields(customer, petStoreCustomer);
        customer.getPetStores().add(petStore);
        petStore.getCustomers().add(customer);
        Customer dbCustomer = customerDao.save(customer);
        return new PetStoreCustomer(dbCustomer);

    }

    private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {

        customer.setCustomerId(petStoreCustomer.getCustomerId());
        customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
        customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
        customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
    }

    private Customer findOrCreateCustomer(Long customerId, Long petStoreId) {
        Customer customer;
        if (Objects.isNull(customerId)) {
            customer = new Customer();
        } else {
            customer = findCustomerById(customerId, petStoreId);
        }
        return customer;

    }

    private Customer findCustomerById(Long customerId, Long petStoreId) {
        Customer customer = customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException(
                "CustomerId not found " + customerId));

        boolean found = false;
        for (PetStore petStore : customer.getPetStores()) {
            if (petStore.getPetStoreId() == petStoreId) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException(

                    "Pet Store with ID=" + petStoreId + " is not associated with customer with ID=" + customerId);
        }
        return customer;
    }

    @Transactional(readOnly = true)
    public List<PetStoreData> retrieveAllPetStores() {
        List<PetStore> petStores = petStoreDao.findAll();
        List<PetStoreData> result = new ArrayList<>();

        for (PetStore petStore : petStores) {
            PetStoreData psd = new PetStoreData(petStore);

            psd.getCustomers().clear();
            psd.getEmployees().clear();

            result.add(psd);

        }

        return result;

    }

    @Transactional(readOnly = true)
    public PetStoreData retrievePetStoreById(Long petStoreId) {

        PetStore petStore = findPetStoreById(petStoreId);
        return new PetStoreData(petStore);
    }

    @Transactional(readOnly = false)
    public void deletePetStoreById(Long petStoreId) {
        PetStore petStore = findPetStoreById(petStoreId);
        petStoreDao.delete(petStore);
    }

}
