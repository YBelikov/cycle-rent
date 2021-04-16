package com.belikov.valteris.cycle.orderBicycle.impl;

import com.belikov.valteris.cycle.bicycle.impl.BicycleMapper;
import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.order.impl.OrderMapper;
import com.belikov.valteris.cycle.orderBicycle.model.OrderBicycle;
import com.belikov.valteris.cycle.orderBicycle.model.OrderBicycleDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderBicycleMapper implements Mapper<OrderBicycleDTO, OrderBicycle> {
    private final OrderMapper orderMapper;
    private final BicycleMapper bicycleMapper;

    @Override
    public OrderBicycleDTO mapEntityToDomain(OrderBicycle entity) {
        if (entity == null) {
            return null;
        }
        OrderBicycleDTO orderBicycleDTO = new OrderBicycleDTO();
        orderBicycleDTO.setId(entity.getId());
        orderBicycleDTO.setBicycleDTO(bicycleMapper.mapEntityToDomain(entity.getBicycle()));
        orderBicycleDTO.setOrderDTO(orderMapper.mapEntityToDomain(entity.getOrder()));
        orderBicycleDTO.setTimeStart(entity.getTimeStart());
        orderBicycleDTO.setTimeEnd(entity.getTimeEnd());
        return orderBicycleDTO;
    }

    @Override
    public OrderBicycle mapDomainToEntity(OrderBicycleDTO domain) {
        if (domain == null) {
            return null;
        }
        OrderBicycle orderBicycle = new OrderBicycle();
        orderBicycle.setId(domain.getId());
        orderBicycle.setBicycle(bicycleMapper.mapDomainToEntity(domain.getBicycleDTO()));
        orderBicycle.setOrder(orderMapper.mapDomainToEntity(domain.getOrderDTO()));
        orderBicycle.setTimeStart(domain.getTimeStart());
        orderBicycle.setTimeEnd(domain.getTimeEnd());
        return orderBicycle;
    }
}
