package com.belikov.valteris.cycle.detail;

import com.belikov.valteris.cycle.detail.model.Detail;
import com.belikov.valteris.cycle.detail.model.DetailDTO;

import java.util.List;
import java.util.Optional;

public interface DetailService {
    DetailDTO save(DetailDTO newDetail);

    List<DetailDTO> getAll();

    Optional<DetailDTO> getById(Long id);

    Optional<DetailDTO> getByName(String name);

    void delete(Long id);
}
