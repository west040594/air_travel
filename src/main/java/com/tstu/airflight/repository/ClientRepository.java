package com.tstu.airflight.repository;

import com.tstu.airflight.model.Client;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ClientRepository extends Neo4jRepository<Client, Long> {
    Client findByUser_username(String username);
}
