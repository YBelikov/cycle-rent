package com.belikov.valteris.cycle.admin;

import com.belikov.valteris.cycle.FileUtils;
import com.belikov.valteris.cycle.bicycle.BicycleService;
import com.belikov.valteris.cycle.bicycle.model.Bicycle;
import com.belikov.valteris.cycle.bicycle.model.BicycleDTO;
import com.belikov.valteris.cycle.bicycle.model.BicycleType;
import com.belikov.valteris.cycle.detail.DetailService;
import com.belikov.valteris.cycle.detail.model.DetailDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {
    private final String PATH_TO_BICYCLES_IMAGE_DIR = "images/bicycles/";
    private final String PATH_TO_DETAILS_IMAGE_DIR = "images/details/";
    private final BicycleService bicycleService;
    private final DetailService detailService;

    @GetMapping("/admin")
    public String adminPage() {
        return "bicycles-admin-page";
    }

    @GetMapping("/bicycle-editor/{id}")
    public String itemEditor(@PathVariable Long id, Model model) {
        BicycleDTO bicycle = bicycleService.getById(id).orElse(new BicycleDTO());
        model.addAttribute("bicycle", bicycle);
        return "bicycle-editor";
    }

    @GetMapping("/details-admin/{id}")
    public String detailsAdmin(@PathVariable Long id, Model model) {
        model.addAttribute("bicycle", bicycleService.getById(id).orElse(new BicycleDTO()));
        return "details-admin-page";
    }

    @GetMapping("/detail-editor/{bicycleId}/{id}")
    public String detailEditor(@PathVariable Long bicycleId, @PathVariable Long id, Model model) {
        model.addAttribute("bicycle", bicycleService.getById(bicycleId).orElse(new BicycleDTO()));
        model.addAttribute("detail", detailService.getById(id).orElse(new DetailDTO()));
        return "detail-editor";
    }

    @GetMapping("/detail-editor/{bicycleId}")
    public String newDetailEditor(@PathVariable Long bicycleId, Model model) {
        model.addAttribute("bicycle", bicycleService.getById(bicycleId).orElse(new BicycleDTO()));
        model.addAttribute("detail", new DetailDTO());
        return "detail-editor";
    }

    @PostMapping("/admin/bicycle/add")
    public String addBicycle(@RequestParam("image") MultipartFile photoFile,
                             @Valid @ModelAttribute("bicycle") BicycleDTO newBicycle,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "bicycle-editor";
        }
        if (newBicycle.getPhoto() == null || newBicycle.getPhoto().isEmpty()) {
            String photo = StringUtils.cleanPath(photoFile.getOriginalFilename());
            newBicycle.setPhoto(photo);
            try {
                FileUtils.saveFile(PATH_TO_BICYCLES_IMAGE_DIR, newBicycle.getPhoto(), photoFile);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                return "redirect:/admin";
            }
        }
        String typeAsString = bicycleTypeAsRegularString(BicycleType.valueOf(newBicycle.getType()));
        newBicycle.setType(typeAsString);
        bicycleService.save(newBicycle);
        return "redirect:/admin";
    }

    @GetMapping("/admin/bicycle/remove/{id}")
    public String removeBicycle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        BicycleDTO bicycleDTO = bicycleService.getById(id).orElse(new BicycleDTO());
        try {
            FileUtils.removeFile(PATH_TO_BICYCLES_IMAGE_DIR, bicycleDTO.getPhoto());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return "redirect:/admin";
        }
        bicycleService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/bicycle/{id}/detail/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addDetail(@PathVariable Long id,
                            @RequestParam("image") MultipartFile photoFile,
                            @Valid @ModelAttribute("detail") DetailDTO newDetail,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "detail-editor";
        }
        if (newDetail.getPhoto() == null || newDetail.getPhoto().isEmpty()) {
            String photo = StringUtils.cleanPath(photoFile.getOriginalFilename());
            newDetail.photo = photo;
            try {
                FileUtils.saveFile(PATH_TO_DETAILS_IMAGE_DIR, photo, photoFile);
            } catch (IOException ioException) {
                return "redirect:/details-admin/" + id.toString();
            }
        }
        DetailDTO detailDTO = bicycleService.getById(id).map(bicycle -> {
            Long detailId = newDetail.getId();
            if (detailId != null) {
                DetailDTO _detailDTO = detailService.getById(detailId).orElseThrow(() -> new ResourceNotFoundException("Not found detail with such id!"));
                bicycle.getDetailDTOS().add(_detailDTO);
                bicycleService.save(bicycle);
                return _detailDTO;
            }
            DetailDTO saved = detailService.save(newDetail);
            bicycle.getDetailDTOS().add(saved);
            bicycleService.save(bicycle);
            return saved;
        }).orElseThrow(() -> new ResourceNotFoundException("Not found bicycle with such id!"));

        return "redirect:/details-admin/" + id.toString();
    }

    @GetMapping("/admin/{bicycleId}/details/remove/{id}")
    public String removeDetail(@PathVariable Long bicycleId,
                               @PathVariable Long id,
                               RedirectAttributes redirectAttributes) {
        try {
            BicycleDTO bicycle = bicycleService.getById(bicycleId).orElse(new BicycleDTO());
            bicycle.getDetailDTOS().removeIf(detailDTO -> detailDTO.getId() == id);
            bicycleService.save(bicycle);
            DetailDTO detailToDelete = detailService.getById(id).orElse(new DetailDTO());
            detailService.delete(id);
            if (detailToDelete.getPhoto() != null && !detailToDelete.getPhoto().isEmpty()) {
                FileUtils.removeFile(PATH_TO_DETAILS_IMAGE_DIR, detailToDelete.getPhoto());
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }

    private String bicycleTypeAsRegularString(BicycleType bicycleType) {
        switch (bicycleType) {
            case MOUNTAIN:
                return "Mountain";
            case CITY:
                return "City";
            case FOR_KIDS:
                return "For kids";
            default:
                return "All";
        }
    }
}
