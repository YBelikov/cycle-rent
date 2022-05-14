package com.belikov.valteris.cycle.bicycleDetail;

import com.belikov.valteris.cycle.bicycleDetail.impl.BicycleDetailDTO;
import com.belikov.valteris.cycle.bicycleDetail.model.BicycleDetail;

public interface BicycleDetailService {
    BicycleDetail save(BicycleDetailDTO bicycleDetailDTO);
    void delete(BicycleDetailDTO bicycleDetailDTO);
}
