package com.tstu.airflight.controller.rest;

import com.tstu.airflight.components.Cart;
import com.tstu.airflight.dto.cart.CartAddItem;
import com.tstu.airflight.dto.cart.CartDto;
import com.tstu.airflight.model.Booking;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/booking", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingRestController {

    private final Cart cart;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> addItemToCart(@RequestBody CartAddItem item) {
        cart.addItem(modelMapper.map(item, Booking.class));
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping("/{seatId}")
    public ResponseEntity<?> deleteItemFromCart(@PathVariable("seatId") Long seatId) {
        cart.deleteItem(seatId);
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllItemsFromCart() {
        cart.deleteAll();
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        return ResponseEntity.ok(cartDto);
    }
}
