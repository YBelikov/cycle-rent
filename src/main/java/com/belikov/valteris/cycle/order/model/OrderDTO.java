package com.belikov.valteris.cycle.order.model;

import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import com.belikov.valteris.cycle.user.model.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private OrderStatus status;
    private UserDTO userDTO;
    private List<BicycleDTO> bicycleDTOS;
    private List<DetailDTO> detailDTOS;
}
