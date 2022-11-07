package com.devsupdesafio3.desafio3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsupdesafio3.desafio3.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
