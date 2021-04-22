package com.belikov.valteris.cycle.detail.impl;

import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.detail.model.Detail;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import org.springframework.stereotype.Component;

@Component
public class DetailMapper implements Mapper<DetailDTO, Detail> {

    @Override
    public DetailDTO mapEntityToDomain(Detail entity) {
        if (entity == null) {
            return null;
        }
        DetailDTO detailDTO = new DetailDTO();
        detailDTO.setId(entity.getId());
        detailDTO.setName(entity.getName());
        detailDTO.setPrice(entity.getPrice());
        detailDTO.setPhoto(entity.getPhoto());
        detailDTO.setDescription(entity.getDescription());
        return detailDTO;
    }

    @Override
    public Detail mapDomainToEntity(DetailDTO domain) {
        if (domain == null) {
            return null;
        }
        Detail detail = new Detail();
        detail.setId(domain.getId());
        detail.setName(domain.getName());
        detail.setPrice(domain.getPrice());
        detail.setPhoto(domain.getPhoto());
        detail.setDescription(domain.getDescription());
        return detail;
    }
}
