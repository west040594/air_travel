package com.tstu.airflight.service.impl;

import com.tstu.airflight.dto.forms.BookingForm;
import com.tstu.airflight.model.Client;
import com.tstu.airflight.repository.ClientRepository;
import com.tstu.airflight.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.neo4j.storageengine.api.EntityType;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return clientRepository.findById(id,2)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.NODE, id));
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        return clientRepository.save(client);
    }


    @Override
    public Client findByUsername(String username) {
        return clientRepository.findByUser_username(username);
    }
}
