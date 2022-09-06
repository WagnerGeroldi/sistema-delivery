package com.wagnerstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wagnerstack.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}