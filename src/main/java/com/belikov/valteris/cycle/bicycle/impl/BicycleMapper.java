package com.belikov.valteris.cycle.bicycle.impl;

import com.belikov.valteris.cycle.bicycle.model.Bicycle;
import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.detail.impl.DetailMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BicycleMapper implements Mapper<BicycleDTO, Bicycle> {
    private final DetailMapper detailMapper;

    @Override
    public BicycleDTO mapEntityToDomain(Bicycle entity) {
        if (entity == null) {
            return null;
        }
        BicycleDTO bicycleDTO = new BicycleDTO();
        bicycleDTO.setId(entity.getId());
        bicycleDTO.setName(entity.getName());
        bicycleDTO.setType(entity.getType());
        bicycleDTO.setWeight(entity.getWeight());
        bicycleDTO.setNumOfSpeeds(entity.getNumOfSpeeds());
        bicycleDTO.setPrice(entity.getPrice());
        bicycleDTO.setPhoto(entity.getPhoto());
        bicycleDTO.setDescription(entity.getDescription());
        bicycleDTO.setDetailDTOS(entity.getDetails().stream()
            .map(detailMapper::mapEntityToDomain).collect(Collectors.toList()));
        return bicycleDTO;
    }

    @Override
    public Bicycle mapDomainToEntity(BicycleDTO domain) {
        if (domain == null) {
            return null;
        }
        Bicycle bicycle = new Bicycle();
        bicycle.setId(domain.getId());
        bicycle.setName(domain.getName());
        bicycle.setType(domain.getType());
        bicycle.setWeight(domain.getWeight());
        bicycle.setNumOfSpeeds(domain.getNumOfSpeeds());
        bicycle.setPrice(domain.getPrice());
        bicycle.setPhoto(domain.getPhoto());
        bicycle.setDescription(domain.getDescription());
        bicycle.setDetails(domain.getDetailDTOS().stream()
            .map(detailMapper::mapDomainToEntity).collect(Collectors.toList()));
        return bicycle;
    }
}
