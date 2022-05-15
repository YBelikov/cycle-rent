package com.belikov.valteris.cycle.bicycleDetail.impl;

import com.belikov.valteris.cycle.bicycleDetail.BicycleDetailRepository;
import com.belikov.valteris.cycle.bicycleDetail.BicycleDetailService;
import com.belikov.valteris.cycle.bicycleDetail.model.BicycleDetail;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BicycleDetailServiceImpl implements BicycleDetailService {
    private final BicycleDetailRepository bicycleDetailRepository;
    private final BicycleDetailMapper bicycleDetailMapper;
    @Override
    public void deleteByDetailId(Long detailId) {
        bicycleDetailRepository.deleteBicycleDetailByDetail_Id(detailId);
    }

    @Override
    public List<BicycleDetailDTO> getByDetailId(Long id) {
        List<BicycleDetail> bicycleDetails = bicycleDetailRepository.getBicycleDetailByDetail_Id(id);
        List<BicycleDetailDTO> result = bicycleDetails.stream().map(bicycleDetailMapper::mapEntityToDomain).collect(Collectors.toList());
        return result;
    }
}
