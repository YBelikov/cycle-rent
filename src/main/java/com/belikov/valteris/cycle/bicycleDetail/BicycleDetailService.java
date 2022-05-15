package com.belikov.valteris.cycle.bicycleDetail;

import com.belikov.valteris.cycle.bicycleDetail.impl.BicycleDetailDTO;
import com.belikov.valteris.cycle.bicycleDetail.model.BicycleDetail;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import java.util.List;

public interface BicycleDetailService {
    //BicycleDetail save(BicycleDetailDTO bicycleDetailDTO);
    void deleteByDetailId(Long id);
    List<BicycleDetailDTO> getByDetailId(Long id);
}
