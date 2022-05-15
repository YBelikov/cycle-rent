package com.belikov.valteris.cycle.bicycle.model;

import com.belikov.valteris.cycle.detail.model.DetailDTO;
import com.belikov.valteris.cycle.orderBicycle.model.OrderBicycleDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BicycleDTO {

    public void removeDetail(Long id) {
        detailDTOS.removeIf(detailDTO -> {
            return detailDTO.getId() == id;
        });
    }

    private Long id;
    @NotBlank(message = "Name of bicycle is required")
    private String name;

    @NotBlank(message = "Type of bicycle is required")
    private String type;

    @Min(value = 1, message = "Weight of the bicycle must be greater than 1.0 kg")
    @Max(value = 138, message = "Weight of the bicycle must be greater than 138.0 kg")
    private double weight;

    @Min(value = 1, message = "Number of bicycle's speeds must be greater than 1")
    @Max(value = 33, message = "Number of bicycle's speeds must be less than 34")
    private int numOfSpeeds;

    @Min(value = 0, message = "Rental price must be greater than 0")
    private double price;

    private String photo;

    @NotBlank(message = "Bicycle's description is required")
    private String description;

    private List<OrderBicycleDTO> orderBicycleDTOS = new ArrayList<>();
    private List<DetailDTO> detailDTOS = new ArrayList<>();
}
