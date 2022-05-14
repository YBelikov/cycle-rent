package com.belikov.valteris.cycle.detail;

import com.belikov.valteris.cycle.detail.model.Detail;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    Optional<Detail> getByName(String name);
    Optional<Detail> save(DetailDTO detail);
}
