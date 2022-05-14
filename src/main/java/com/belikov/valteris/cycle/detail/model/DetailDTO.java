package com.belikov.valteris.cycle.detail.model;

import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DetailDTO {
    public Long id;

    @NotBlank(message = "Detail should have a name")
    public String name;

    @NotBlank(message = "Detail should have a description")
    public String description;

    public String photo;

    @Min(value = 0, message = "Rental price must be greater than 0")
    public double price;

    public List<OrderDTO> orderDTOS;
    public List<BicycleDTO> bicycleDTOS;
}
