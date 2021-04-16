package com.belikov.valteris.cycle.order.model;

import com.belikov.valteris.cycle.detail.model.DetailDTO;
import com.belikov.valteris.cycle.orderBicycle.model.OrderBicycleDTO;
import com.belikov.valteris.cycle.place.model.PlaceDTO;
import com.belikov.valteris.cycle.user.model.UserDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private OrderStatus status;
    private UserDTO userDTO;
    private PlaceDTO placeDTO;
    private List<OrderBicycleDTO> orderBicycleDTOS = new ArrayList<>();
    private List<DetailDTO> detailDTOS;
}