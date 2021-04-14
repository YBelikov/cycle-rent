package com.belikov.valteris.cycle.order;

import com.belikov.valteris.cycle.bicycle.BicycleService;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import com.belikov.valteris.cycle.order.model.OrderStatus;
import com.belikov.valteris.cycle.order_bicycle.OrderBicycleService;
import com.belikov.valteris.cycle.order_bicycle.model.OrderBicycleDTO;
import com.belikov.valteris.cycle.user.model.UserDTO;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userDTO"})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;
    private final OrderBicycleService orderBicycleService;

    @GetMapping("/cart")
    public String showCartPage() {
        return "cart-page";
    }

    @GetMapping("/order/formed")
    @ResponseBody
    public String getFormedOrderBicyclesAndDetails(@ModelAttribute("userDTO") UserDTO userDTO) {
        final Optional<OrderDTO> formedOrder = orderService.findByUserDTOAndStatus(userDTO, OrderStatus.FORMED);
        List<DetailDTO> detailDTOS = formedOrder.get().getDetailDTOS();
        List<OrderBicycleDTO> orderBicycleDTOS = orderBicycleService.findAllByOrder(formedOrder.get());

        JSONObject json = new JSONObject();
        json.put("bicycles", orderBicycleDTOS);
        json.put("details", detailDTOS);

        return json.toString();
    }

}
