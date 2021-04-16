package com.belikov.valteris.cycle.order;

import com.belikov.valteris.cycle.detail.DetailService;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import com.belikov.valteris.cycle.order.model.OrderStatus;
import com.belikov.valteris.cycle.orderBicycle.OrderBicycleService;
import com.belikov.valteris.cycle.orderBicycle.model.OrderBicycleDTO;
import com.belikov.valteris.cycle.place.PlaceService;
import com.belikov.valteris.cycle.user.model.UserDTO;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userDTO"})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;
    private final DetailService detailService;
    private final PlaceService placeService;
    private final OrderBicycleService orderBicycleService;

    @ModelAttribute(name = "userDTO")
    public UserDTO userDTO() {
        return new UserDTO();
    }

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
        json.put("place", formedOrder.get().getPlaceDTO().getPlace());
        json.put("totalPrice", countTotalPrice(orderBicycleDTOS, detailDTOS));

        return json.toString();
    }

    @DeleteMapping("/orderBicycle/delete/{orderBicycleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderBicycleById(@PathVariable Long orderBicycleId, @ModelAttribute("userDTO") UserDTO userDTO) {
        orderBicycleService.delete(orderBicycleId);
    }

    @DeleteMapping("/detail/all/delete/{detailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllDetailsById(@PathVariable Long detailId, @ModelAttribute("userDTO") UserDTO userDTO) {
        final Optional<OrderDTO> formedOrder = orderService.findByUserDTOAndStatus(userDTO, OrderStatus.FORMED);
        List<DetailDTO> detailDTOS = formedOrder.get().getDetailDTOS()
            .stream().filter(detailDTO -> !detailDTO.getId().equals(detailId)).collect(Collectors.toList());
        formedOrder.get().setDetailDTOS(detailDTOS);
        orderService.save(formedOrder.get());
    }

    @PutMapping("/detail/remove/{detailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeDetailById(@PathVariable Long detailId, @ModelAttribute("userDTO") UserDTO userDTO) {
        final Optional<OrderDTO> formedOrder = orderService.findByUserDTOAndStatus(userDTO, OrderStatus.FORMED);

        DetailDTO detailDTO = formedOrder.get().getDetailDTOS().stream()
            .filter(dto -> dto.getId().equals(detailId))
            .findAny()
            .orElse(null);
        formedOrder.get().getDetailDTOS().remove(detailDTO);
        orderService.save(formedOrder.get());
    }

    @PutMapping("/detail/add/{detailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addDetailById(@PathVariable Long detailId, @ModelAttribute("userDTO") UserDTO userDTO) {
        final Optional<OrderDTO> formedOrder = orderService.findByUserDTOAndStatus(userDTO, OrderStatus.FORMED);
        DetailDTO detailDTO = detailService.getById(detailId).orElse(null);
        formedOrder.get().getDetailDTOS().add(detailDTO);
        orderService.save(formedOrder.get());
    }

    @GetMapping("/checkout")
    public String showCheckoutPage() {
        return "checkout-page";
    }

    @PutMapping("/checkout")
    @ResponseStatus(HttpStatus.OK)
    public void sendCheckoutInformation(@RequestBody String placeInfo, @ModelAttribute("userDTO") UserDTO userDTO) {
        JSONObject jsonObject = new JSONObject(placeInfo);
        Long placeId = jsonObject.getLong("placeId");

        final Optional<OrderDTO> formedOrder = orderService.findByUserDTOAndStatus(userDTO, OrderStatus.FORMED);
        formedOrder.get().setPlaceDTO(placeService.getById(placeId).get());
        orderService.save(formedOrder.get());
    }

    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.OK)
    public void makeCheckout(@ModelAttribute("userDTO") UserDTO userDTO) {
        final Optional<OrderDTO> formedOrder = orderService.findByUserDTOAndStatus(userDTO, OrderStatus.FORMED);
        formedOrder.get().setStatus(OrderStatus.PAYED);
        orderService.save(formedOrder.get());
        createNewOrder(userDTO);
    }

    @GetMapping("/myOrders")
    public String showOrdersPage() {
        return "my-orders";
    }

    @GetMapping("/order/payed/page/{pageNumber}")
    @ResponseBody
    public String getPayedOrders(@PathVariable int pageNumber, @ModelAttribute("userDTO") UserDTO userDTO) {
        final Page<OrderDTO> orderPage = orderService.findPageByUserDTOAndStatus(userDTO, OrderStatus.PAYED, pageNumber);
        final int totalPages = orderPage.getTotalPages();
        pageNumber = checkNumberOfPage(pageNumber, totalPages);

        JSONObject json = new JSONObject();
        json.put("currentPage", pageNumber);
        json.put("totalPages", totalPages);
        json.put("orders", orderPage.get().collect(Collectors.toList()));

        return json.toString();
    }

    private double countTotalPrice(List<OrderBicycleDTO> orderBicycleDTOS, List<DetailDTO> detailDTOS) {
        double totalPrice = orderBicycleDTOS.stream()
            .mapToDouble(dto -> dto.getBicycleDTO().getPrice() * getTimeDiff(dto.getTimeEnd(), dto.getTimeStart()))
            .sum();
        totalPrice += detailDTOS.stream().mapToDouble(DetailDTO::getPrice).sum();

        return totalPrice;
    }

    private double getTimeDiff(LocalTime timeEnd, LocalTime timeStart) {
        double timeDiff = timeEnd.getHour() - timeStart.getHour();
        if (timeEnd.getMinute() == 30) {
            timeDiff += 0.5;
        }
        if (timeStart.getMinute() == 30) {
            timeDiff -= 0.5;
        }
        return timeDiff;
    }

    public void createNewOrder(UserDTO userDTO) {
        final OrderDTO order = new OrderDTO();
        order.setStatus(OrderStatus.FORMED);
        order.setUserDTO(userDTO);
        order.setDetailDTOS(new ArrayList<>());
        order.setPlaceDTO(placeService.getById(1L).get());
        orderService.save(order);
    }

    private int checkNumberOfPage(int numberOfPage, int totalPages) {
        if (numberOfPage < 1 || numberOfPage > totalPages) {
            numberOfPage = 1;
        }
        return numberOfPage;
    }

}
