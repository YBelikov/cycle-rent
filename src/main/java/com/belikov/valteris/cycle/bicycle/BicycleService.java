package com.belikov.valteris.cycle.bicycle;

import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.bicycle.model.BicycleType;
import com.belikov.valteris.cycle.bicycle.model.SortType;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BicycleService {
    List<BicycleDTO> getAll();

    Optional<BicycleDTO> getById(Long id);

    void save(BicycleDTO newBicycle);

    void delete(Long id);

    Page<BicycleDTO> findSortedPage(SortType typeOfSort, BicycleType bicycleType, int numberOfPage);

    Page<BicycleDTO> getBicyclesLike(String example);
}
