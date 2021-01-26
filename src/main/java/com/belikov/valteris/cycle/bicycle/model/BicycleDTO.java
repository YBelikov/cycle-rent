package com.belikov.valteris.cycle.bicycle.model;

import com.belikov.valteris.cycle.detail.model.DetailDTO;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BicycleDTO {
    private Long id;
    private String name;
    private String type;
    private double weight;
    private int numOfSpeeds;
    private double price;
    private String photo;
    private String description;
    private List<OrderDTO> orderDTOS;
    private List<DetailDTO> detailDTOS;
}
