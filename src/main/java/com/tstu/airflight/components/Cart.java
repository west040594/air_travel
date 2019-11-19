package com.tstu.airflight.components;

import com.tstu.airflight.model.Booking;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Data
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class Cart {
    private Map<Long, Booking> items = new HashMap<>();
    private BigDecimal total = new BigDecimal(0);
    private int itemCount = 0;

    public Cart() {
    }

    public void addItem(Booking booking) {
        if(!items.containsKey(booking.getSeat().getId())) {
            items.put(booking.getSeat().getId(), booking);
            refreshCartFields();
        }
    }


    public void deleteItem(Long seatId) {
        items.remove(seatId);
        refreshCartFields();
    }

    public void deleteAll() {
        items.clear();
        refreshCartFields();
    }

    private void refreshCartFields() {
        this.itemCount = items.size();
        this.total = items.values().stream()
                .map(booking -> booking.getSeat().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Booking> getItemList() {
        return new ArrayList<>(items.values());
    }
}
