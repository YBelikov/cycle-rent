package com.belikov.valteris.cycle.orderBicycle.model;

import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderBicycleDTO {
    private Long id;
    private OrderDTO orderDTO;
    private BicycleDTO bicycleDTO;
    private LocalTime timeStart;
    private LocalTime timeEnd;
}
