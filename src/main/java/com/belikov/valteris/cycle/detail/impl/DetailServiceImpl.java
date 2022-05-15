package com.belikov.valteris.cycle.detail.impl;

import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.detail.DetailRepository;
import com.belikov.valteris.cycle.detail.DetailService;
import com.belikov.valteris.cycle.detail.model.Detail;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DetailServiceImpl implements DetailService {

    private final DetailRepository detailRepository;
    private final Mapper<DetailDTO, Detail> detailMapper;
//
//    public void setDetailRepository(DetailRepository detailRepository) {
//        this.detailRepository = detailRepository;
//    }
//
//    public void setDetailMapper(Mapper<DetailDTO, Detail> detailMapper) {
//        this.detailMapper = detailMapper;
//    }

    @Override
    public DetailDTO save(DetailDTO newDetail) {
       return detailMapper.mapEntityToDomain(detailRepository.save(detailMapper.mapDomainToEntity(newDetail)));
    }

    @Override
    public List<DetailDTO> getAll() {
        return detailRepository.findAll().stream()
            .map(detailMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<DetailDTO> getById(Long id) {
        return detailRepository.findById(id).map(detailMapper::mapEntityToDomain);
    }

    @Override
    public Optional<DetailDTO> getByName(String name) {
        return detailRepository.getByName(name).map(detailMapper::mapEntityToDomain);
    }

    @Override
    public void delete(Long id) {
        detailRepository.deleteById(id);
    }
}
