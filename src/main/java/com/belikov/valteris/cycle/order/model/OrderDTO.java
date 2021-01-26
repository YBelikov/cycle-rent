package com.belikov.valteris.cycle.order.model;

import com.belikov.valteris.cycle.detail.model.DetailDTO;
import com.belikov.valteris.cycle.order_bicycle.model.OrderBicycleDTO;
import com.belikov.valteris.cycle.user.model.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private OrderStatus status;
    private UserDTO userDTO;
    private List<OrderBicycleDTO> orderBicycleDTOS;
    private List<DetailDTO> detailDTOS;
}
