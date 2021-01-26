package com.belikov.valteris.cycle.detail;

import com.belikov.valteris.cycle.detail.model.DetailDTO;

import java.util.List;
import java.util.Optional;

public interface DetailService {
    void save(DetailDTO newDetail);

    List<DetailDTO> getAll();

    Optional<DetailDTO> getById(Long id);

    void delete(Long id);
}
