package com.belikov.valteris.cycle.order_bicycle;

import com.belikov.valteris.cycle.order_bicycle.model.OrderBicycleDTO;

import java.util.List;
import java.util.Optional;

public interface OrderBicycleService {
    void save(OrderBicycleDTO orderBicycleDTO);

    List<OrderBicycleDTO> getAll();

    Optional<OrderBicycleDTO> getById(Long id);

    void delete(Long id);
}
