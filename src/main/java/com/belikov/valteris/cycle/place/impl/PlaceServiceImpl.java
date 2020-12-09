package com.belikov.valteris.cycle.place.impl;

import com.belikov.valteris.cycle.place.PlaceRepository;
import com.belikov.valteris.cycle.place.PlaceService;
import com.belikov.valteris.cycle.place.model.Place;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Override
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }
}
