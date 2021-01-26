package com.belikov.valteris.cycle.order.impl;

import com.belikov.valteris.cycle.bicycle.impl.BicycleMapper;
import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.detail.impl.DetailMapper;
import com.belikov.valteris.cycle.order.model.Order;
import com.belikov.valteris.cycle.order.model.OrderDTO;
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
    private final BicycleMapper bicycleMapper;

    @Override
    public OrderDTO mapEntityToDomain(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(entity.getId());
        orderDTO.setTimeStart(entity.getTimeStart());
        orderDTO.setTimeEnd(entity.getTimeEnd());
        orderDTO.setStatus(entity.getStatus());
        orderDTO.setUserDTO(userMapper.mapEntityToDomain(entity.getUser()));
        orderDTO.setBicycleDTOS(entity.getBicycles().stream()
                .map(bicycleMapper::mapEntityToDomain).collect(Collectors.toList()));
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
        order.setTimeStart(domain.getTimeStart());
        order.setTimeEnd(domain.getTimeEnd());
        order.setStatus(domain.getStatus());
        order.setUser(userMapper.mapDomainToEntity(domain.getUserDTO()));
        order.setBicycles(domain.getBicycleDTOS().stream()
                .map(bicycleMapper::mapDomainToEntity).collect(Collectors.toList()));
        order.setDetails(domain.getDetailDTOS().stream()
                .map(detailMapper::mapDomainToEntity).collect(Collectors.toList()));
        return order;
    }
}
