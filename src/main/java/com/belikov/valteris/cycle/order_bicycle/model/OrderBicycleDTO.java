package com.belikov.valteris.cycle.order_bicycle.model;

import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderBicycleDTO {
    private Long id;
    private OrderDTO orderDTO;
    private BicycleDTO bicycleDTO;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
}
