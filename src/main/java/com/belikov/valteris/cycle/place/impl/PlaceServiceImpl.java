package com.belikov.valteris.cycle.place.impl;

import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.place.PlaceRepository;
import com.belikov.valteris.cycle.place.PlaceService;
import com.belikov.valteris.cycle.place.model.Place;
import com.belikov.valteris.cycle.place.model.PlaceDTO;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final Mapper<PlaceDTO, Place> placeMapper;

    @Override
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public Optional<PlaceDTO> getById(Long id) {
        return placeRepository.findById(id).map(placeMapper::mapEntityToDomain);
    }
}
