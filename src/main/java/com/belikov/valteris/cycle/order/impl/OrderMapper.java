package com.belikov.valteris.cycle.order.impl;

import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.detail.impl.DetailMapper;
import com.belikov.valteris.cycle.order.model.Order;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import com.belikov.valteris.cycle.place.impl.PlaceMapper;
import com.belikov.valteris.cycle.user.impl.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderMapper implements Mapper<OrderDTO, Order> {

    private final UserMapper userMapper;
    private final PlaceMapper placeMapper;
    private final DetailMapper detailMapper;

    @Override
    public OrderDTO mapEntityToDomain(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(entity.getId());
        orderDTO.setStatus(entity.getStatus());
        orderDTO.setUserDTO(userMapper.mapEntityToDomain(entity.getUser()));
        orderDTO.setPlaceDTO(placeMapper.mapEntityToDomain(entity.getPlace()));
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
        order.setPlace(placeMapper.mapDomainToEntity(domain.getPlaceDTO()));
        order.setDetails(domain.getDetailDTOS().stream()
                .map(detailMapper::mapDomainToEntity).collect(Collectors.toList()));
        return order;
    }
}
