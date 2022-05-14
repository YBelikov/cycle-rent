package com.belikov.valteris.cycle.bicycle.impl;

import com.belikov.valteris.cycle.bicycle.BicycleRepository;
import com.belikov.valteris.cycle.bicycle.BicycleService;
import com.belikov.valteris.cycle.bicycle.model.Bicycle;
import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.bicycle.model.BicycleType;
import com.belikov.valteris.cycle.bicycle.model.SortType;
import com.belikov.valteris.cycle.config.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BicycleServiceImpl implements BicycleService {

    private static final int ITEMS_PER_PAGE = 3;
    private final BicycleRepository bicycleRepository;
    private final Mapper<BicycleDTO, Bicycle> bicycleMapper;

    @Override
    public List<BicycleDTO> getAll() {
        return bicycleRepository.findAll().stream()
            .map(bicycleMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<BicycleDTO> getById(Long id) {
        return bicycleRepository.findById(id).map(bicycleMapper::mapEntityToDomain);
    }

    @Override
    public BicycleDTO save(BicycleDTO newBicycle) {
        if (newBicycle.getId() != null) {
            // return bicycleMapper.mapEntityToDomain(bicycleRepository.update(bicycleMapper.mapDomainToEntity(newBicycle)));
        }
        return bicycleMapper.mapEntityToDomain(bicycleRepository.save(bicycleMapper.mapDomainToEntity(newBicycle)));
    }

    @Override
    public void delete(Long id) {
        bicycleRepository.deleteById(id);
    }

    @Override
    public Page<BicycleDTO> findSortedPage(SortType typeOfSort, BicycleType bicycleType, int numberOfPage) {
        Pageable bicyclePage = PageRequest.of(numberOfPage - 1, ITEMS_PER_PAGE);
        if (typeOfSort.equals(SortType.PRICE_UP)) {
            bicyclePage = PageRequest.of(numberOfPage - 1, ITEMS_PER_PAGE, Sort.by("price"));
        } else if (typeOfSort.equals(SortType.PRICE_DOWN)) {
            bicyclePage = PageRequest.of(numberOfPage - 1, ITEMS_PER_PAGE, Sort.by("price").descending());
        } else if (typeOfSort.equals(SortType.WEIGHT_UP)) {
            bicyclePage = PageRequest.of(numberOfPage - 1, ITEMS_PER_PAGE, Sort.by("weight"));
        } else if (typeOfSort.equals(SortType.WEIGHT_DOWN)) {
            bicyclePage = PageRequest.of(numberOfPage - 1, ITEMS_PER_PAGE, Sort.by("weight").descending());
        }

        if (bicycleType.equals(BicycleType.ALL)) {
            return bicycleRepository.findAll(bicyclePage).map(bicycleMapper::mapEntityToDomain);
        }
        return bicycleRepository.findAllByType(bicycleType.getStringType(), bicyclePage)
            .map(bicycleMapper::mapEntityToDomain);
    }

    @Override
    public Page<BicycleDTO> getBicyclesLike(String example) {
        Pageable bicyclePage = PageRequest.of(0, ITEMS_PER_PAGE);
        return bicycleRepository.findAllByNameLikeOrderByName("%" + example + "%", bicyclePage)
            .map(bicycleMapper::mapEntityToDomain);
    }
}
