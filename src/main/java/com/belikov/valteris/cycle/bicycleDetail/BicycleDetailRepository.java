package com.belikov.valteris.cycle.bicycleDetail;

import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.bicycleDetail.impl.BicycleDetailDTO;
import com.belikov.valteris.cycle.bicycleDetail.model.BicycleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BicycleDetailRepository extends JpaRepository<BicycleDetail, Long> {
   // List<BicycleDetail> getBicycleDetailByDetailId(Long detailId);
    void deleteBicycleDetailByDetail_Id(Long detailId);
    List<BicycleDetail> getBicycleDetailByDetail_Id(Long id);
}
