package com.belikov.valteris.cycle.order_bicycle;

import com.belikov.valteris.cycle.order_bicycle.model.OrderBicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBicycleRepository extends JpaRepository<OrderBicycle, Long> {
}
