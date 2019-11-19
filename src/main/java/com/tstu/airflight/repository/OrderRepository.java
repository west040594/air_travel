package com.tstu.airflight.repository;

import com.tstu.airflight.model.Order;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OrderRepository extends Neo4jRepository<Order, Long> {
}
