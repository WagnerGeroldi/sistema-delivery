package com.wagnerstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wagnerstack.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
