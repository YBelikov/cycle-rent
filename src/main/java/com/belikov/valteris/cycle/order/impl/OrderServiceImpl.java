package com.belikov.valteris.cycle.order.impl;

import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.order.OrderRepository;
import com.belikov.valteris.cycle.order.OrderService;
import com.belikov.valteris.cycle.order.model.Order;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final Mapper<OrderDTO, Order> orderMapper;

    @Override
    public void save(OrderDTO newOrder) {
        orderRepository.save(orderMapper.mapDomainToEntity(newOrder));
    }

    @Override
    public List<OrderDTO> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDTO> getById(Long id) {
        return orderRepository.findById(id).map(orderMapper::mapEntityToDomain);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
