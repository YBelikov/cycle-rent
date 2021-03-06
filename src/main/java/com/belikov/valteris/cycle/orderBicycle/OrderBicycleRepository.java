package com.belikov.valteris.cycle.orderBicycle;

import com.belikov.valteris.cycle.order.model.Order;
import com.belikov.valteris.cycle.orderBicycle.model.OrderBicycle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBicycleRepository extends JpaRepository<OrderBicycle, Long> {

    List<OrderBicycle> findAllByOrder(Order order);
}
