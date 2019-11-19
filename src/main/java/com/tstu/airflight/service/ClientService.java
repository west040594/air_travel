package com.tstu.airflight.service;

import com.tstu.airflight.dto.forms.BookingForm;
import com.tstu.airflight.model.Client;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;

import java.util.List;

public interface ClientService {
    Client findById(Long id) throws EntityNotFoundException;
    List<Client> findAll();
    Client create(Client client);
    Client update(Client client);
    Client findByUsername(String username);
}
