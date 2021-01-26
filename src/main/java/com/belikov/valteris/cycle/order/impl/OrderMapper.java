package com.belikov.valteris.cycle.order.impl;

import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.detail.impl.DetailMapper;
import com.belikov.valteris.cycle.order.model.Order;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import com.belikov.valteris.cycle.order_bicycle.impl.OrderBicycleMapper;
import com.belikov.valteris.cycle.user.impl.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderMapper implements Mapper<OrderDTO, Order> {
    private final UserMapper userMapper;
    private final DetailMapper detailMapper;
    private final OrderBicycleMapper orderBicycleMapper;

    @Override
    public OrderDTO mapEntityToDomain(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(entity.getId());
        orderDTO.setStatus(entity.getStatus());
        orderDTO.setUserDTO(userMapper.mapEntityToDomain(entity.getUser()));
        orderDTO.setOrderBicycleDTOS(entity.getBicycles().stream()
                .map(orderBicycleMapper::mapEntityToDomain).collect(Collectors.toList()));
        orderDTO.setDetailDTOS(entity.getDetails().stream()
                .map(detailMapper::mapEntityToDomain).collect(Collectors.toList()));
        return orderDTO;
    }

    @Override
    public Order mapDomainToEntity(OrderDTO domain) {
        if (domain == null) {
            return null;
        }
        Order order = new Order();
        order.setId(domain.getId());
        order.setStatus(domain.getStatus());
        order.setUser(userMapper.mapDomainToEntity(domain.getUserDTO()));
        order.setBicycles(domain.getOrderBicycleDTOS().stream()
                .map(orderBicycleMapper::mapDomainToEntity).collect(Collectors.toList()));
        order.setDetails(domain.getDetailDTOS().stream()
                .map(detailMapper::mapDomainToEntity).collect(Collectors.toList()));
        return order;
    }
}
