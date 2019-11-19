package com.tstu.airflight.dto.cart;

import com.tstu.airflight.dto.BookingDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class CartDto {
    private Map<Long, BookingDto> items;
    private String total;
    private int itemCount;

    public List<BookingDto> getItemList() {
        return new ArrayList<>(items.values());
    }
}
