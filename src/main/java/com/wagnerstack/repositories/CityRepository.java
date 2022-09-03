package com.wagnerstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wagnerstack.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
