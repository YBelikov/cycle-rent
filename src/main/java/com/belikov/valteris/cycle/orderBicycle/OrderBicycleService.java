package com.belikov.valteris.cycle.orderBicycle;

import com.belikov.valteris.cycle.order.model.OrderDTO;
import com.belikov.valteris.cycle.orderBicycle.model.OrderBicycleDTO;

import java.util.List;
import java.util.Optional;

public interface OrderBicycleService {

    void save(OrderBicycleDTO orderBicycleDTO);

    List<OrderBicycleDTO> getAll();

    Optional<OrderBicycleDTO> getById(Long id);

    void delete(Long id);

    List<OrderBicycleDTO> findAllByOrder(OrderDTO orderDTO);
}
