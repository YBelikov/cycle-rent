package com.belikov.valteris.cycle.detail.impl;

import com.belikov.valteris.cycle.bicycle.impl.BicycleMapper;
import com.belikov.valteris.cycle.bicycle.model.Bicycle;
import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.config.Mapper;
import com.belikov.valteris.cycle.detail.model.Detail;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import com.belikov.valteris.cycle.order.impl.OrderMapper;
import com.belikov.valteris.cycle.order.model.Order;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DetailMapper implements Mapper<DetailDTO, Detail> {
   @Override
    public DetailDTO mapEntityToDomain(Detail entity) {
        if (entity == null) {
            return null;
        }
        DetailDTO detailDTO = new DetailDTO();
        detailDTO.setId(entity.getId());
        detailDTO.setName(entity.getName());
        detailDTO.setPrice(entity.getPrice());
        detailDTO.setPhoto(entity.getPhoto());
        detailDTO.setDescription(entity.getDescription());

        List<BicycleDTO> bicycleDTOs = new ArrayList<>();
        List<OrderDTO> orderDTOs = new ArrayList<>();

//        for (Bicycle bicycle : entity.getBicycles()) {
//            bicycleDTOs.add(bicycleMapper.mapEntityToDomain(bicycle));
//        }
//
//        for (Order order : entity.getOrders()) {
//            orderDTOs.add(orderMapper.mapEntityToDomain(order));
//        }

        detailDTO.setBicycleDTOS(bicycleDTOs);
        detailDTO.setOrderDTOS(orderDTOs);
        return detailDTO;
    }

    @Override
    public Detail mapDomainToEntity(DetailDTO domain) {
        if (domain == null) {
            return null;
        }
        Detail detail = new Detail();
        detail.setId(domain.getId());
        detail.setName(domain.getName());
        detail.setPrice(domain.getPrice());
        detail.setPhoto(domain.getPhoto());
        detail.setDescription(domain.getDescription());
        return detail;
    }
}
