package pet.store.dao;

import pet.store.entity.PetStore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetStoreDao extends JpaRepository<PetStore, Long> {

}
