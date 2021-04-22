package com.belikov.valteris.cycle.place;

import com.belikov.valteris.cycle.place.model.Place;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"user"})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/allPlaces")
    @ResponseBody
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }
}
