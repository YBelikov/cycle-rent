package com.belikov.valteris.cycle.order;

import com.belikov.valteris.cycle.order.model.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void save(OrderDTO newOrder);

    List<OrderDTO> getAll();

    Optional<OrderDTO> getById(Long id);

    void delete(Long id);
}
