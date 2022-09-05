package com.wagnerstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wagnerstack.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
