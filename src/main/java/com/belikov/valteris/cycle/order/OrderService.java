package com.belikov.valteris.cycle.order;

import com.belikov.valteris.cycle.order.model.OrderDTO;
import com.belikov.valteris.cycle.order.model.OrderStatus;
import com.belikov.valteris.cycle.user.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void save(OrderDTO newOrder);

    List<OrderDTO> getAll();

    Optional<OrderDTO> getById(Long id);

    void delete(Long id);

    Optional<OrderDTO> findByUserDTOAndStatus(UserDTO userDTO, OrderStatus orderStatus);
}
