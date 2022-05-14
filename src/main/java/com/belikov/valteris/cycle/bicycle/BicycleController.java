package com.belikov.valteris.cycle.bicycle;

import com.belikov.valteris.cycle.FileUtils;
import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.bicycle.model.BicycleType;
import com.belikov.valteris.cycle.bicycle.model.SortType;
import com.belikov.valteris.cycle.detail.DetailService;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import com.belikov.valteris.cycle.order.OrderService;
import com.belikov.valteris.cycle.order.model.OrderDTO;
import com.belikov.valteris.cycle.order.model.OrderStatus;
import com.belikov.valteris.cycle.orderBicycle.OrderBicycleService;
import com.belikov.valteris.cycle.orderBicycle.model.OrderBicycleDTO;
import com.belikov.valteris.cycle.place.PlaceService;
import com.belikov.valteris.cycle.user.UserService;
import com.belikov.valteris.cycle.user.model.UserDTO;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({"userDTO"})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BicycleController {
    private final BicycleService bicycleService;
    private final UserService userService;
    private final OrderService orderService;
    private final DetailService detailService;
    private final PlaceService placeService;
    private final OrderBicycleService orderBicycleService;

    @ModelAttribute(name = "userDTO")
    public UserDTO userDTO() {
        return new UserDTO();
    }

    @PostMapping("/index")
    public String performLogin(@ModelAttribute UserDTO user, RedirectAttributes attributes) {
        user = userService.findByUsername(user.getUsername()).orElse(null);
        attributes.addFlashAttribute("userDTO", user);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showHomePage(RedirectAttributes attributes, @ModelAttribute("userDTO") UserDTO userDTO, Model model) {
        final Optional<OrderDTO> formedOrder = orderService.findByUserDTOAndStatus(userDTO, OrderStatus.FORMED);
        if (!formedOrder.isPresent()) {
            createNewOrder(userDTO);
        }
        model.addAttribute("userDTO", userDTO);
        return "index";
    }

    @GetMapping("/bicycles")
    public String bicyclesListPage() {
        return "bicycle-list";
    }

    @GetMapping("/bicycles/all/sort/{typeOfSort}/type/{bicycleType}/page/{numberOfPage}")
    @ResponseBody
    public String getSortedPageOfBicycles(@PathVariable String typeOfSort,
                                          @PathVariable String bicycleType, @PathVariable int numberOfPage) {
        final Page<BicycleDTO> bicyclePage = bicycleService.findSortedPage(SortType.valueOf(typeOfSort),
            BicycleType.valueOf(bicycleType), numberOfPage);
        final int totalPages = bicyclePage.getTotalPages();
        numberOfPage = checkNumberOfPage(numberOfPage, totalPages);

        return getJson(numberOfPage, bicyclePage, totalPages);
    }

    @GetMapping("/api/bicycles/{id}/details")
    @ResponseBody
    public String detailsForBicycle(@PathVariable Long id) {
        BicycleDTO bicycleDTO = bicycleService.getById(id).orElse(new BicycleDTO());
        List<DetailDTO> detailDTOS = bicycleDTO.getDetailDTOS();
        JSONObject json = new JSONObject();
        json.put("details", detailDTOS);
        return json.toString();
    }

    @GetMapping("/api/details/all")
    @ResponseBody
    public String allDetails() {
        List<DetailDTO> allDetails = detailService.getAll();
        JSONObject json = new JSONObject();
        json.put("details", allDetails);
        return json.toString();
    }

    @GetMapping("/api/details/{id}")
    @ResponseBody
    public DetailDTO detail(@PathVariable Long id) {
        DetailDTO detail = detailService.getById(id).orElse(new DetailDTO());
        return detail;
    }

    @GetMapping("/bicycles/all")
    @ResponseBody
    public String allBicycles() {
        List<BicycleDTO> allBicycles = bicycleService.getAll();
        JSONObject json = new JSONObject();
        json.put("bicycles", allBicycles);
        return json.toString();
    }

    @PostMapping("/bicycleName")
    @ResponseBody
    public String getBicycleNames(@RequestBody String example) {
        final Page<BicycleDTO> bicyclePage = bicycleService.getBicyclesLike(example);
        int totalPages = bicyclePage.getTotalPages();
        if (totalPages == 0) {
            totalPages = 1;
        }
        return getJson(1, bicyclePage, totalPages);
    }

    @GetMapping("/bicycle/{id}")
    public String bicyclePage(@PathVariable Long id, Model model) {
        model.addAttribute("bicycle", bicycleService.getById(id).orElse(null));
        return "bicycle-page";
    }

    @GetMapping("/api/bicycle/{id}")
    @ResponseBody
    public BicycleDTO getBicycle(@PathVariable Long id) {
        return bicycleService.getById(id).get();
    }

    @PostMapping(value = "/bicycle/totalPrice", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTotalPrice(@RequestBody String data) {
        double totalValue = countTotalValue(data);
        JSONObject json = new JSONObject();
        json.put("totalValue", totalValue);
        return json.toString();
    }

    @PostMapping(value = "/bicycle/addToBasket", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addBicycleToBasket(@RequestBody String data, @ModelAttribute("userDTO") UserDTO userDTO,
                                     RedirectAttributes attributes) {
        JSONObject jsonData = new JSONObject(data);
        final long bicycleId = jsonData.getLong("bicycleId");
        final Optional<BicycleDTO> bicycleDTO = bicycleService.getById(bicycleId);
        final Optional<OrderDTO> formedOrder = orderService.findByUserDTOAndStatus(userDTO, OrderStatus.FORMED);

        saveOrderBicycleDTO(jsonData, bicycleDTO, formedOrder);

        final Map<String, Object> detailPrice = jsonData.getJSONObject("optionPrice").toMap();
        for (String detail : detailPrice.keySet()) {
            formedOrder.ifPresent(orderDTO -> orderDTO.getDetailDTOS()
                .add(detailService.getById(Long.valueOf(detail.substring(5))).get()));
        }
        formedOrder.ifPresent(orderService::save);

        JSONObject json = new JSONObject();
        return json.toString();
    }

    @PostMapping("/bicycle/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody BicycleDTO newBicycle) {
        bicycleService.save(newBicycle);
    }

    @DeleteMapping("/bicycle/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bicycleService.delete(id);
    }

    private int checkNumberOfPage(int numberOfPage, int totalPages) {
        if (numberOfPage < 1 || numberOfPage > totalPages) {
            numberOfPage = 1;
        }
        return numberOfPage;
    }

    private void createNewOrder(UserDTO userDTO) {
        final OrderDTO order = new OrderDTO();
        order.setStatus(OrderStatus.FORMED);
        order.setUserDTO(userDTO);
        order.setDetailDTOS(new ArrayList<>());
        order.setPlaceDTO(placeService.getById(1L).get());
        orderService.save(order);
    }

    private void saveOrderBicycleDTO(JSONObject jsonData, Optional<BicycleDTO> bicycleDTO,
                                     Optional<OrderDTO> formedOrder) {
        final OrderBicycleDTO orderBicycleDTO = new OrderBicycleDTO();
        formedOrder.ifPresent(orderBicycleDTO::setOrderDTO);
        bicycleDTO.ifPresent(orderBicycleDTO::setBicycleDTO);
        orderBicycleDTO.setTimeStart(getLocalTime(jsonData.getDouble("start")));
        orderBicycleDTO.setTimeEnd(getLocalTime(jsonData.getDouble("end")));
        orderBicycleService.save(orderBicycleDTO);
    }

    private double countTotalValue(@RequestBody String data) {
        final JSONObject jsonData = new JSONObject(data);
        final Optional<BicycleDTO> bicycleDTO = bicycleService.getById(jsonData.getLong("bicycleId"));

        double totalValue = 0;
        if (bicycleDTO.isPresent()) {
            double timeDifference = jsonData.getDouble("end") - jsonData.getDouble("start");
            totalValue = bicycleDTO.get().getPrice() * timeDifference;
        }
        final Map<String, Object> optionPrice = jsonData.getJSONObject("optionPrice").toMap();
        for (Object price : optionPrice.values()) {
            totalValue += (Integer) price;
        }
        return totalValue;
    }

    private LocalTime getLocalTime(double time) {
        final int hours = (int) time;
        int minutes = 0;
        if (time - hours != 0) {
            minutes = 30;
        }
        return LocalTime.of(hours, minutes);
    }

    private String getJson(@PathVariable int numberOfPage, Page<BicycleDTO> bicyclePage, int totalPages) {
        JSONObject json = new JSONObject();
        json.put("currentPage", numberOfPage);
        json.put("totalPages", totalPages);
        json.put("bicycles", bicyclePage.get().collect(Collectors.toList()));
        return json.toString();
    }
}