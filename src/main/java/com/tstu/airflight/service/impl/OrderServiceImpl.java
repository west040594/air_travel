package com.tstu.airflight.service.impl;

import com.tstu.airflight.components.Cart;
import com.tstu.airflight.dto.forms.BookingForm;
import com.tstu.airflight.model.Booking;
import com.tstu.airflight.model.Client;
import com.tstu.airflight.model.Order;
import com.tstu.airflight.model.enums.SeatStatus;
import com.tstu.airflight.repository.OrderRepository;
import com.tstu.airflight.service.ClientService;
import com.tstu.airflight.service.FlightSeatService;
import com.tstu.airflight.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.neo4j.storageengine.api.EntityType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final FlightSeatService flightSeatService;
    private final Cart cart;

    @Override
    public Order findById(Long id) throws EntityNotFoundException {
        return orderRepository.findById(id, 5)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.NODE, id));
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll(4);
    }

    @Override
    public Order create(BookingForm bookingForm) throws EntityNotFoundException {
        Order order = new Order();
        Client client = clientService.findById(bookingForm.getClientId());
        order.setClient(client);
        order.setBookings(cart.getItemList());
        order.setPrice(cart.getTotal());
        order.setClientData(formOrderClientData(bookingForm));
        client.setBalance(client.getBalance().subtract(order.getPrice()));
        Order savedOrder = orderRepository.save(order, 4);
        savedOrder.getBookings().stream()
                .map(Booking::getSeat)
                .forEach(flightSeat -> flightSeatService.changeStatus(flightSeat, SeatStatus.CLOSED));
        cart.deleteAll();
        return savedOrder;
    }



    private String formOrderClientData(BookingForm bookingForm) {
        return Strings.join(Arrays.asList(
                bookingForm.getFirstName(),
                bookingForm.getLastName(),
                bookingForm.getPassport(),
                bookingForm.getPhone()), ',');
    }
}
