package com.belikov.valteris.cycle.place;

import com.belikov.valteris.cycle.place.model.Place;
import com.belikov.valteris.cycle.place.model.PlaceDTO;
import java.util.List;
import java.util.Optional;

public interface PlaceService {

    List<Place> getAllPlaces();

    Optional<PlaceDTO> getById(Long id);
}
