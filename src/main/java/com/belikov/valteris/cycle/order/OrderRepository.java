package com.belikov.valteris.cycle.order;

import com.belikov.valteris.cycle.order.model.Order;
import com.belikov.valteris.cycle.order.model.OrderStatus;
import com.belikov.valteris.cycle.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order>findByUserAndStatus(User user, OrderStatus orderStatus);
}
