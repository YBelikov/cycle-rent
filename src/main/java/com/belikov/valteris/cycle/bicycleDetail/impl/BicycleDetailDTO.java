package com.belikov.valteris.cycle.bicycleDetail.impl;

import com.belikov.valteris.cycle.bicycle.model.Bicycle;
import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.detail.model.Detail;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BicycleDetailDTO {
    Long id;
    BicycleDTO bicycle;
    DetailDTO detail;
}
