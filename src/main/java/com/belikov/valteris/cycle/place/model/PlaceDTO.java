package com.belikov.valteris.cycle.place.model;

import com.belikov.valteris.cycle.order.model.OrderDTO;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceDTO {

    private Long id;
    private String place;
    private double lat;
    private double len;
    private List<OrderDTO> orderDTOS;
}
