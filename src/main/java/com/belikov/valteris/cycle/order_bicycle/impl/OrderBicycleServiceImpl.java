package com.belikov.valteris.cycle.order_bicycle.impl;

import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.order.model.Order;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import com.belikov.valteris.cycle.order_bicycle.OrderBicycleRepository;
import com.belikov.valteris.cycle.order_bicycle.OrderBicycleService;
import com.belikov.valteris.cycle.order_bicycle.model.OrderBicycle;
import com.belikov.valteris.cycle.order_bicycle.model.OrderBicycleDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderBicycleServiceImpl implements OrderBicycleService {

    private final OrderBicycleRepository orderBicycleRepository;
    private final Mapper<OrderBicycleDTO, OrderBicycle> orderBicycleMapper;
    private final Mapper<OrderDTO, Order> orderMapper;

    @Override
    public void save(OrderBicycleDTO orderBicycleDTO) {
        orderBicycleRepository.save(orderBicycleMapper.mapDomainToEntity(orderBicycleDTO));
    }

    @Override
    public List<OrderBicycleDTO> getAll() {
        return orderBicycleRepository.findAll().stream()
            .map(orderBicycleMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderBicycleDTO> getById(Long id) {
        return orderBicycleRepository.findById(id).map(orderBicycleMapper::mapEntityToDomain);
    }

    @Override
    public void delete(Long id) {
        orderBicycleRepository.deleteById(id);
    }

    @Override
    public List<OrderBicycleDTO> findAllByOrder(OrderDTO orderDTO) {
        return orderBicycleRepository.findAllByOrder(orderMapper.mapDomainToEntity(orderDTO)).stream()
            .map(orderBicycleMapper::mapEntityToDomain).collect(Collectors.toList());
    }
}
