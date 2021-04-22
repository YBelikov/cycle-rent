package com.belikov.valteris.cycle.detail.model;

import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DetailDTO {

    private Long id;
    private String name;
    private String description;
    private String photo;
    private double price;
    private List<OrderDTO> orderDTOS;
    private List<BicycleDTO> bicycleDTOS;
}
