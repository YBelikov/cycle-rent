package com.belikov.valteris.cycle.admin;


import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateBicycleDetailsDTO {
    Long bicycleId;
    List<Long> detailsDetail;
}
