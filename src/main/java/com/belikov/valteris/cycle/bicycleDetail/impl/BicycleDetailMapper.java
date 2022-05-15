package com.belikov.valteris.cycle.bicycleDetail.impl;

import com.belikov.valteris.cycle.bicycle.impl.BicycleMapper;
import com.belikov.valteris.cycle.bicycleDetail.model.BicycleDetail;
import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.detail.impl.DetailMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BicycleDetailMapper implements Mapper<BicycleDetailDTO, BicycleDetail> {
    private final DetailMapper detailMapper;
    private final BicycleMapper bicycleMapper;

    @Override
    public BicycleDetailDTO mapEntityToDomain(BicycleDetail bicycleDetail) {
        BicycleDetailDTO bicycleDetailDTO = new BicycleDetailDTO();
        bicycleDetailDTO.setId(bicycleDetail.getId());
        bicycleDetailDTO.setBicycle(bicycleMapper.mapEntityToDomain(bicycleDetail.getBicycle()));
        bicycleDetailDTO.setDetail(detailMapper.mapEntityToDomain(bicycleDetail.getDetail()));
        return bicycleDetailDTO;
    }

    @Override
    public BicycleDetail mapDomainToEntity(BicycleDetailDTO bicycleDetailDTO) {
        BicycleDetail bicycleDetail = new BicycleDetail();
        bicycleDetail.setId(bicycleDetailDTO.getId());
        bicycleDetail.setBicycle(bicycleMapper.mapDomainToEntity(bicycleDetailDTO.getBicycle()));
        bicycleDetail.setDetail(detailMapper.mapDomainToEntity(bicycleDetailDTO.getDetail()));
        return bicycleDetail;
    }

}
