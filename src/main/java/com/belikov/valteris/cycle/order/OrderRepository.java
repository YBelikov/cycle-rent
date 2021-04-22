package com.belikov.valteris.cycle.order;

import com.belikov.valteris.cycle.order.model.Order;
import com.belikov.valteris.cycle.order.model.OrderStatus;
import com.belikov.valteris.cycle.user.model.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByUserAndStatus(User user, OrderStatus orderStatus);

    Page<Order> findAllByUserAndStatus(User user, OrderStatus orderStatus, Pageable pageable);
}
