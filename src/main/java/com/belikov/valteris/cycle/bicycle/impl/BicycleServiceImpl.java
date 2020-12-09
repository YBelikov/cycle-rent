package com.belikov.valteris.cycle.bicycle.impl;

import com.belikov.valteris.cycle.bicycle.BicycleRepository;
import com.belikov.valteris.cycle.bicycle.BicycleService;
import com.belikov.valteris.cycle.bicycle.model.Bicycle;
import com.belikov.valteris.cycle.bicycle.model.BicycleType;
import com.belikov.valteris.cycle.bicycle.model.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BicycleServiceImpl implements BicycleService {

    private static final int ITEMS_PER_PAGE = 3;
    private final BicycleRepository bicycleRepository;

    @Override
    public List<Bicycle> getAll() {
        return bicycleRepository.findAll();
    }

    @Override
    public Optional<Bicycle> getById(Long id) {
        return bicycleRepository.findById(id);
    }

    @Override
    public void save(Bicycle newBicycle) {
        bicycleRepository.save(newBicycle);
    }

    @Override
    public void delete(Long id) {
        bicycleRepository.deleteById(id);
    }

    @Override
    public Page<Bicycle> findSortedPage(SortType typeOfSort, BicycleType bicycleType, int numberOfPage) {
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

        if(bicycleType.equals(BicycleType.ALL)) {
            return bicycleRepository.findAll(bicyclePage);
        }
        return bicycleRepository.findAllByType(bicycleType.getStringType(), bicyclePage);
    }

    @Override
    public Page<Bicycle> getBicyclesLike(String example) {
        Pageable bicyclePage = PageRequest.of(0, ITEMS_PER_PAGE);
        return bicycleRepository.findAllByNameLikeOrderByName("%" + example + "%", bicyclePage);
    }
}
