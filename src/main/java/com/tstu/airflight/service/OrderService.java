package com.tstu.airflight.service;

import com.tstu.airflight.dto.forms.BookingForm;
import com.tstu.airflight.model.Order;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;

import java.util.List;

public interface OrderService {
    Order findById(Long id) throws EntityNotFoundException;
    List<Order> findAll();
    Order create(BookingForm bookingForm) throws EntityNotFoundException;
}
