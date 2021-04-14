package com.belikov.valteris.cycle.place.impl;

import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.place.model.Place;
import com.belikov.valteris.cycle.place.model.PlaceDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlaceMapper implements Mapper<PlaceDTO, Place>  {

    @Override
    public PlaceDTO mapEntityToDomain(Place entity) {
        if (entity == null) {
            return null;
        }
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(entity.getId());
        placeDTO.setPlace(entity.getPlace());
        placeDTO.setLat(entity.getLat());
        placeDTO.setLen(entity.getLen());
        return placeDTO;
    }

    @Override
    public Place mapDomainToEntity(PlaceDTO domain) {
        if (domain == null) {
            return null;
        }
        Place place = new Place();
        place.setId(domain.getId());
        place.setPlace(domain.getPlace());
        place.setLat(domain.getLat());
        place.setLen(domain.getLen());
        return place;
    }
}
