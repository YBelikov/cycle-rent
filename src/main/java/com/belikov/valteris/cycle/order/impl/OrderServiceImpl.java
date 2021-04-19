package com.belikov.valteris.cycle.order.impl;

import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.order.OrderRepository;
import com.belikov.valteris.cycle.order.OrderService;
import com.belikov.valteris.cycle.order.model.Order;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import com.belikov.valteris.cycle.order.model.OrderStatus;
import com.belikov.valteris.cycle.user.model.User;
import com.belikov.valteris.cycle.user.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private static final int ITEMS_PER_PAGE = 3;
    private final OrderRepository orderRepository;
    private final Mapper<OrderDTO, Order> orderMapper;
    private final Mapper<UserDTO, User> userMapper;

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

    @Override
    public Optional<OrderDTO> findByUserDTOAndStatus(UserDTO userDTO, OrderStatus orderStatus) {
        return orderRepository.findByUserAndStatus(userMapper.mapDomainToEntity(userDTO), orderStatus)
            .map(orderMapper::mapEntityToDomain);
    }

    @Override
    public Page<OrderDTO> findPageByUserDTOAndStatus(UserDTO userDTO, OrderStatus orderStatus, int numberOfPage) {
        Pageable orderPage = PageRequest.of(numberOfPage - 1, ITEMS_PER_PAGE);
        return orderRepository.findAllByUserAndStatus(userMapper.mapDomainToEntity(userDTO), orderStatus, orderPage)
            .map(orderMapper::mapEntityToDomain);
    }
}
