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
    public Long id;
    public String name;
    public String description;
    public String photo;
    public double price;
    public List<OrderDTO> orderDTOS;
    public List<BicycleDTO> bicycleDTOS;
}
